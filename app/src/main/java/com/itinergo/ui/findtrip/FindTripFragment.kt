package com.itinergo.ui.findtrip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itinergo.R
import com.itinergo.databinding.FragmentFindTripBinding

class FindTripFragment : Fragment() {
    private var _binding: FragmentFindTripBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFindTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
    }

    private fun setButton() {
        binding.ivAddListFtrip.setOnClickListener {
            findNavController().navigate(R.id.action_findTripFragment_to_addTripFragment)
        }
    }
}