package com.itinergo.ui.savedplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.databinding.FragmentVoilaSavedPlaceBinding

class VoilaSavedPlaceFragment : Fragment() {

    private var _binding: FragmentVoilaSavedPlaceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoilaSavedPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setButton()
    }

    private fun setButton() {
        binding.btnToHome1.setOnClickListener {
            findNavController().navigate(R.id.action_voilaSavedPlaceFragment_to_navigation_home)
        }
    }

}