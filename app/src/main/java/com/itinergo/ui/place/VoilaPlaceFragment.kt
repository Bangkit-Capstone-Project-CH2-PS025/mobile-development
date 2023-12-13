package com.itinergo.ui.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itinergo.R
import com.itinergo.databinding.FragmentVoilaPlaceBinding

class VoilaPlaceFragment : Fragment() {
    private var _binding: FragmentVoilaPlaceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentVoilaPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
    }

    private fun setButton() {
        binding.btnToHome1.setOnClickListener {
            findNavController().navigate(R.id.action_voilaPlaceFragment_to_navigation_home)
        }
    }
}