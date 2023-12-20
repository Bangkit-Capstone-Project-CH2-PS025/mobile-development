package com.itinergo.ui.travelbudgeting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.adapter.PlaceAdapter
import com.itinergo.adapter.TravelBudgetingAdapter
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentTravelBudgetingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TravelBudgetingFragment : Fragment(), TravelBudgetingAdapter.ListPlaceInterface {

    private var _binding : FragmentTravelBudgetingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : TravelBudgetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelBudgetingViewModel::class.java]
        _binding = FragmentTravelBudgetingBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setRecyclerView()
        setButton()
    }

    private fun setButton() {
        binding.tvAddBudget.setOnClickListener {
            findNavController().navigate(R.id.action_travelBudgetingFragment_to_addBudgetingFragment)
        }
    }

    private fun setRecyclerView() {
        viewModel.getAllBudgeting()
        viewModel.allBudgetingResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val adapter = TravelBudgetingAdapter(this)
                    binding.rvVisitedPlace.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvVisitedPlace.adapter = adapter
                    adapter.setData(it.data!!.data)

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

    override fun budget(id: String) {

    }

}