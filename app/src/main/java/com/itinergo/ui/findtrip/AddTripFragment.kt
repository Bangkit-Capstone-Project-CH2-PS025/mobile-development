package com.itinergo.ui.findtrip

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.MaterialDatePicker
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentAddTripBinding
import com.itinergo.ui.place.AddPlaceFragment
import com.itinergo.utils.reduceFileImage
import com.itinergo.utils.uriToFile
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddTripFragment : Fragment() {

    private var _binding: FragmentAddTripBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FindTripViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[FindTripViewModel::class.java]
        _binding = FragmentAddTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setButton()
        setPermission()
    }



    private fun setButton() {
        binding.etImageAdd.setOnClickListener {
            openGallery()
        }
        materialPicker()
        binding.btnUploadAddPlace.setOnClickListener {
            val country = binding.etCountryAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val city = binding.etCityAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val departure = binding.etDepartureAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val until = binding.etUntilAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val persons = binding.etPersonsAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            val contact = binding.etContactAdd.text.toString().trim()
                .toRequestBody("multipart/form-data".toMediaType())
            currentImageUri?.let { uri ->
                val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
                val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                val imageMultipart = MultipartBody.Part.createFormData(
                    "image",
                    imageFile.name,
                    requestImageFile
                )
                viewModel.createTrip(
                    city,country,departure, until,persons,contact,imageMultipart
                )
            }
            createResult()
        }

    }

private fun materialPicker() {
    binding.etDepartureAdd.setOnClickListener {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
                }
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                binding.etDepartureAdd.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
    binding.etUntilAdd.setOnClickListener {
        val calendar = Calendar.getInstance()

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance().apply {
                    set(Calendar.YEAR, selectedYear)
                    set(Calendar.MONTH, selectedMonth)
                    set(Calendar.DAY_OF_MONTH, selectedDay)
                }
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                binding.etUntilAdd.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}

private fun createResult() {
    viewModel.createTripResult.observe(viewLifecycleOwner) {
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
                    val contact = binding.etContactAdd.text.toString()
                    val bundle = Bundle()
                    bundle.putString("contact", contact)
                    findNavController().navigate(R.id.action_addTripFragment_to_voilaFtripFragment, bundle)
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

private fun openGallery() {
    launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}

private val launcherGallery =
    registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            currentImageUri?.let {
                val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
                binding.etImageAdd.setText(imageFile.name)
            }
        }
    }

private fun setPermission() {
    if (!allPermissionsGranted()) {
        requestPermissionLauncher.launch(REQUIRED_PERMISSION)
    }
}

companion object {
    private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
}

}