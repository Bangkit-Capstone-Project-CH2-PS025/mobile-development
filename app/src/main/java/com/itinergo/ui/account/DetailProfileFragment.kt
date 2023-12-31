package com.itinergo.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentDetailProfileBinding
import com.itinergo.ui.login.LoginActivity
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
        countResult()
        setButton()

    }

    private fun countResult() {
        viewModel.getCountByCity()
        viewModel.cityResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.tvCitiesCount.text = it.data?.data?.count.toString()

                }

                is BaseResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                }

                else -> {

                }
            }
        }
        viewModel.getCountByCountry()
        viewModel.countryResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.tvCountriesCount.text = it.data?.data?.count.toString()

                }

                is BaseResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                }

                else -> {

                }
            }
        }
    }

    private fun setButton() {
        binding.btnLogout.setOnClickListener {
            viewModel.removeName()
            viewModel.removeToken()
            viewModel.removeIsLoginStatus()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

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
                    if(it.data?.data?.images != null) {
                        Glide.with(requireContext())
                            .load(it.data.data.images)
                            .into(binding.ivProfileAccountDetail)
                    } else {
                        Glide.with(requireContext())
                            .asDrawable()
                            .load(ContextCompat.getDrawable(requireContext(), R.drawable.baseline_account_circle_24))
                            .into(binding.ivProfileAccountDetail)
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