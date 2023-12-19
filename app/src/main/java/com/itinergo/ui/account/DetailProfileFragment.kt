package com.itinergo.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentDetailProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProfileFragment : Fragment() {
    private var _binding: FragmentDetailProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        _binding = FragmentDetailProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        profileResult()

    }

    private fun setButton() {

    }

    private fun profileResult() {
        viewModel.getAccount()
        viewModel.profileResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvAccountEmail2.text = it.data?.data?.email
                    binding.tvNameDetailAcc.text = it.data?.data?.name
                    binding.tvExpKind.text = "The ${it.data?.data?.levelTraveler?.name}"
                    binding.tvExpDetail.text = it.data?.data?.xp.toString()
                    it.data?.data?.images.let { image ->
                        if (image != null) {
                            Glide.with(requireContext()).load(image)
                                .into(binding.ivProfileAccountDetail)
                        }
                    }

                    binding.btnChangeProfile.setOnClickListener { _ ->
                        val name = it.data?.data?.name
                        val email = it.data?.data?.email
                        val images = it.data?.data?.images
                        val bundle = Bundle()
                        bundle.putString("name", name)
                        bundle.putString("email", email)
                        bundle.putString("images", images.toString())
                        findNavController().navigate(
                            R.id.action_detailProfileFragment_to_editProfileFragment,
                            bundle
                        )
                    }

                }

                is BaseResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error: ${it.msg}", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
        }
    }


}