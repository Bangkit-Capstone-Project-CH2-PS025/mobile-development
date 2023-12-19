package com.itinergo.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.postitinerary.DataX
import com.itinergo.databinding.FragmentItineraryPlanningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItineraryPlanningFragment : Fragment() {
    private var _binding: FragmentItineraryPlanningBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentItineraryPlanningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setButton()
        setParcelable()
        setText()
        setCarbon()
    }

    private fun setCarbon() {
        viewModel.getCarbon()
        viewModel.getCarbonResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.tvDistance.text = it.data?.data?.totalDistance.toString()
                    binding.tvTransportCost.text = it.data?.data?.busPrice.toString()
                    binding.tvTransportCost1.text = it.data?.data?.motorbikePrice.toString()
                    binding.tvTransportCost2.text = it.data?.data?.carPrice.toString()
                    binding.tvTransportCarbon.text = it.data?.data?.busCo2.toString()
                    binding.tvTransportCarbon1.text = it.data?.data?.motorbikeCo2.toString()
                    binding.tvTransportCarbon2.text = it.data?.data?.carCo2.toString()

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

    private fun setText() {
        val city = arguments?.getString("city")
        val budget = arguments?.getString("budget")
        val duration = arguments?.getString("duration")
        val countPlace = arguments?.getString("count_place")

        binding.tvBudgetResult.text = "With Your IDR $budget for $duration days Trip"
        binding.tvPlaceResult.text = "We Got you $countPlace Places in $city"
    }

    private fun setParcelable() {
        val listDay: DataX? = arguments?.getParcelable("listDay")
        Log.d(TAG, "setParcelable: ${listDay?.day1}")
        listDay?.day1?.get(0)?.placeName.let { placeName ->
            binding.tvDay11.text = placeName
            binding.tvDay11.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("place_name", placeName)
                findNavController().navigate(
                    R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                    bundle
                )
            }
        }
        listDay?.day1?.get(1)?.placeName.let { placeName ->
            binding.tvDay12.text = placeName
            binding.tvDay12.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("place_name", placeName)
                findNavController().navigate(
                    R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                    bundle
                )
            }
        }
        listDay?.day1?.get(2)?.placeName.let { placeName ->
            binding.tvDay13.text = placeName
            binding.tvDay13.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("place_name", placeName)
                findNavController().navigate(
                    R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                    bundle
                )
            }
        }
        listDay?.day2?.get(0)?.placeName.let { placeName ->
            if (placeName != null) {
                binding.tvDay21.text = placeName
                binding.tvDay21.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("place_name", placeName)
                    findNavController().navigate(
                        R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                        bundle
                    )
                }
            }
        }
        listDay?.day2?.get(1)?.placeName.let { placeName ->
            if (placeName != null) {

                binding.tvDay22.text = placeName
                binding.tvDay22.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("place_name", placeName)
                    findNavController().navigate(
                        R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                        bundle
                    )
                }
            }
        }
        listDay?.day2?.get(2)?.placeName.let { placeName ->
            binding.tvDay23.text = placeName
            binding.tvDay23.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("place_name", placeName)
                findNavController().navigate(
                    R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                    bundle
                )
            }
        }
        listDay?.day3?.get(0)?.placeName.let { placeName ->
            if (placeName != null) {

                binding.tvDay31.text = placeName
                binding.tvDay31.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("place_name", placeName)
                    findNavController().navigate(
                        R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                        bundle
                    )
                }
            }
        }
        listDay?.day3?.get(1)?.placeName.let { placeName ->
            if (placeName != null) {
                binding.tvDay32.text = placeName
                binding.tvDay32.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("place_name", placeName)
                    findNavController().navigate(
                        R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                        bundle
                    )
                }
            }
        }
        listDay?.day3?.get(2)?.placeName.let { placeName ->
            if (placeName != null) {
                binding.tvDay33.text = placeName
                binding.tvDay33.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("place_name", placeName)
                    findNavController().navigate(
                        R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,
                        bundle
                    )
                }
            }
        }
    }

    private fun setButton() {
        binding.btnSavePlan.setOnClickListener {
            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_voilaHomeFragment)
        }
    }


}