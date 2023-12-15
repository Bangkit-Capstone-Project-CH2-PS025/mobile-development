package com.itinergo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.VISIBLE

        setButton()
        setText()
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
            findNavController().navigate(R.id.action_navigation_home_to_itineraryPlanningFragment2)
        }
        binding.ivFindTrip.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_findTripFragment)
        }
        binding.ivTravelTips.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_travelTipsFragment)
        }
    }
}