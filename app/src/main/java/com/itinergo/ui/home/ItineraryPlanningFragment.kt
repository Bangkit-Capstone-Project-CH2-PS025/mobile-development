package com.itinergo.ui.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.FragmentHomeBinding
import com.itinergo.databinding.FragmentItineraryPlanningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItineraryPlanningFragment : Fragment() {
    private var _binding: FragmentItineraryPlanningBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeViewModel

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
        getItineraryResult()
    }

    private fun getItineraryResult() {
        viewModel.getAllItinerary()
        viewModel.itineraryResult.observe(viewLifecycleOwner) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    it.data!!.data[0].placeName.let{placeName ->
                        binding.tvDay11.text = placeName
                        binding.tvDay11.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[1].placeName.let{placeName ->
                        binding.tvDay12.text = placeName
                        binding.tvDay12.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[2].placeName.let{placeName ->
                        binding.tvDay13.text = placeName
                        binding.tvDay13.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[3].placeName.let{placeName ->
                        binding.tvDay21.text = placeName
                        binding.tvDay21.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[4].placeName.let{placeName ->
                        binding.tvDay22.text = placeName
                        binding.tvDay22.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[5].placeName.let{placeName ->
                        binding.tvDay23.text = placeName
                        binding.tvDay23.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[6].placeName.let{placeName ->
                        binding.tvDay31.text = placeName
                        binding.tvDay31.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[7].placeName.let{placeName ->
                        binding.tvDay32.text = placeName
                        binding.tvDay32.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
                    it.data.data[8].placeName.let{placeName ->
                        binding.tvDay33.text = placeName
                        binding.tvDay33.setOnClickListener {
                            val bundle = Bundle()
                            bundle.putString("place_name", placeName)
                            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_placeTodayFragment2,bundle)
                        }
                    }
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

    private fun setButton() {
        binding.btnSavePlan.setOnClickListener {
            findNavController().navigate(R.id.action_itineraryPlanningFragment_to_voilaHomeFragment)
        }
    }

}