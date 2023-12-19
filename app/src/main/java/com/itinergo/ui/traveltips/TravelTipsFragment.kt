package com.itinergo.ui.traveltips

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
import com.itinergo.adapter.SavedPlaceAdapter
import com.itinergo.adapter.TravelTipsAdapter
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentTravelTipsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TravelTipsFragment : Fragment(), TravelTipsAdapter.ListPlaceInterface {

    private var _binding: FragmentTravelTipsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TravelTipsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelTipsViewModel::class.java]
        _binding = FragmentTravelTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.VISIBLE
        allTipsResult()
    }

    private fun allTipsResult() {
        viewModel.getAllTravelTips()
        viewModel.travelTipsResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val adapter = TravelTipsAdapter(this)
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


    override fun tips(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        findNavController().navigate(R.id.action_travelTipsFragment_to_detailTravelTipsFragment, bundle)
    }
}