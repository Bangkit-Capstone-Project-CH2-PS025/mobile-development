package com.itinergo.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.adapter.PlaceAdapter
import com.itinergo.adapter.SavedPlaceAdapter
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentPlaceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceFragment : Fragment(), PlaceAdapter.ListPlaceInterface {
    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[PlaceViewModel::class.java]
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.VISIBLE
        setButton()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        viewModel.getAllVisitedPlace()
        viewModel.visitedPlaceResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
//                    val adapter = PlaceAdapter(this)
//                    binding.rvVisitedPlace.layoutManager = LinearLayoutManager(requireContext())
//                    binding.rvVisitedPlace.adapter = adapter
//                    adapter.setData(it.data!!.data)

                    if(it.data?.data?.size != 0) {
                        binding.rvVisitedPlace.visibility = View.VISIBLE
                        binding.tvNotFound2.visibility = View.GONE
                        val adapter = PlaceAdapter(this)
                        binding.rvVisitedPlace.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvVisitedPlace.adapter = adapter
                        adapter.setData(it.data!!.data)
                    } else {
                        binding.rvVisitedPlace.visibility = View.GONE
                        binding.tvNotFound2.visibility = View.VISIBLE
                    }

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

    private fun setButton() {
        binding.tvAddPlace.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_place_to_addPlaceFragment)
        }
    }

    override fun place(id: String) {

    }
}