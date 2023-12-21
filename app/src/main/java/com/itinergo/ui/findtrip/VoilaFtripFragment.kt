package com.itinergo.ui.findtrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.databinding.FragmentVoilaFtripBinding

class VoilaFtripFragment : Fragment() {
    private var _binding: FragmentVoilaFtripBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoilaFtripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setButton()
        setText()
    }

    private fun setText() {
        val contact = arguments?.getString("contact")
        binding.tvContactFtrip.text = contact
    }

    private fun setButton() {
        binding.btnToHome.setOnClickListener {
            findNavController().navigate(R.id.action_voilaFtripFragment_to_navigation_home)
        }
    }
}