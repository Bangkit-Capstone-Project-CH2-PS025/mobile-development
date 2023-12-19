package com.itinergo.ui.account

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentEditProfileBinding
import com.itinergo.ui.forgot.toPx
import com.itinergo.utils.reduceFileImage
import com.itinergo.utils.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

@AndroidEntryPoint
class EditProfileFragment : DialogFragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    private var currentImageUri: Uri? = null

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.apply {
                setLayout(
                    350.toPx(requireContext()),
                    400.toPx(requireContext())
                )
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentEditProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPermission()
        setButton()
        setText()

    }

    private fun setText() {
        val email = arguments?.getString("email")
        val name = arguments?.getString("name")
        binding.edNameEdit.setText(name)
        binding.edEmailEdit.setText(email)
    }

    private fun setPermission() {
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
    }
    private fun setButton() {
        binding.edImageEdit.setOnClickListener {
            openGallery()
        }
        binding.btnUploadEdit.setOnClickListener {
            val email = binding.edEmailEdit.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val name = binding.edNameEdit.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())


            currentImageUri?.let { uri ->
                val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
                val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                val imageMultipart = MultipartBody.Part.createFormData(
                    "images",
                    imageFile.name,
                    requestImageFile
                )
                if(binding.edImageEdit.text.toString().isNotEmpty()){
                    viewModel.updateAccount(
                        name,email,imageMultipart
                    )
                }

            }
            if (binding.edImageEdit.text.toString().isEmpty()) {
                val nameWithoutImage = binding.edNameEdit.text.toString()
                val emailWithoutImage = binding.edEmailEdit.text.toString()
                val images = arguments?.getString("images")
                Log.d(TAG, "setButton: $images")
                viewModel.updateAccountWithoutImage(emailWithoutImage,nameWithoutImage,
                    images.toString()
                )
            }
            uploadResult()
        }
    }
    private fun openGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                currentImageUri?.let {
                    val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
                    binding.edImageEdit.setText(imageFile.name)
                }
            }
        }
    private fun uploadResult() {
        viewModel.updateProfileResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Success")
                    builder.setMessage(it.data?.message)

                    builder.setPositiveButton("OK") { _, _ ->
                        findNavController().navigate(R.id.action_editProfileFragment_to_detailProfileFragment)
                    }
                    val dialog = builder.create()
                    dialog.show()
                }

                is BaseResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Error")
                    builder.setMessage(it.msg)

                    builder.setPositiveButton("OK") { _, _ ->

                    }
                    val dialog = builder.create()
                    dialog.show()
                }

                else -> {
                }
            }
        }
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}