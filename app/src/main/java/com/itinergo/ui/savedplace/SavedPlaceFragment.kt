package com.itinergo.ui.savedplace

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
import com.itinergo.adapter.SavedPlaceAdapter
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.FragmentSavedPlaceBinding
import com.itinergo.ui.place.PlaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedPlaceFragment : Fragment(),
    SavedPlaceAdapter.ListPlaceInterface {
    private var _binding: FragmentSavedPlaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SavedPlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[SavedPlaceViewModel::class.java]
        _binding = FragmentSavedPlaceBinding.inflate(inflater, container, false)
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
        viewModel.getAllSavedPlace()
        viewModel.savedPlaceResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val adapter = SavedPlaceAdapter(this)
                    binding.rvSaved.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvSaved.adapter = adapter
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

    private fun setButton() {
        binding.rvSaved.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_saved_place_to_detailSavedPlaceFragment)
        }
    }

    override fun place(id: String) {

    }


}