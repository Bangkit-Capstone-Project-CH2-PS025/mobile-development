package com.itinergo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.itinergo.R
import com.itinergo.databinding.FragmentHomeBinding
import com.itinergo.databinding.FragmentItineraryPlanningBinding


class ItineraryPlanningFragment : Fragment() {
    private var _binding: FragmentItineraryPlanningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItineraryPlanningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
    }

    private fun setButton() {
        binding.btnSavePlan.setOnClickListener {
            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_voilaHomeFragment)
        }
        binding.ivDefaultBgPlan.setOnClickListener {
            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2)
        }
    }

}