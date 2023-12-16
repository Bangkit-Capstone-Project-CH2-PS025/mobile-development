package com.itinergo.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.VISIBLE

        val username = activity?.intent?.getStringExtra("username")
        binding.tvUsernameIntro.text = "Hi $username"

        setButton()
        setText()
        postItineraryResult()
    }

    private fun postItineraryResult() {
        viewModel.itineraryResult.observe(viewLifecycleOwner) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    findNavController().navigate(R.id.action_navigation_home_to_itineraryPlanningFragment2)
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

    @SuppressLint("SetTextI18n")
    private fun setText() {
        val firstPreferences = arguments?.getString("first_preferences")
        val secondPreferences = arguments?.getString("second_preferences")
        if (firstPreferences != null) {
            binding.etPreference.setText("$firstPreferences, $secondPreferences")
        }
    }

    private fun setButton() {
        binding.etPreference.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_planFragment)
        }

        binding.ivViewHome.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_placeTodayFragment)
        }
        binding.btnSearch.setOnClickListener {
            val firstPreferences = arguments?.getString("first_preferences")
            val secondPreferences = arguments?.getString("second_preferences")
            val city = binding.etCity.text.toString()
            val duration = binding.etDuration.text.toString().toInt()
            val budget = binding.etBudget.text.toString().toInt()
            val preferences1 = firstPreferences.toString().lowercase(Locale.getDefault())
            val preferences2 = secondPreferences.toString().lowercase(Locale.getDefault())

            viewModel.postItinerary(city,budget, duration, preferences1, preferences2)
        }
        binding.ivFindTrip.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_findTripFragment)
        }
        binding.ivTravelTips.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_travelTipsFragment)
        }
    }
}