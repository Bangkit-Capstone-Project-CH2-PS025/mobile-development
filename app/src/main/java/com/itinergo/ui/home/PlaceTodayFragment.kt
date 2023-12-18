package com.itinergo.ui.home

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentPlaceTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceTodayFragment : Fragment() {
    private var _binding: FragmentPlaceTodayBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentPlaceTodayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navBar?.visibility = View.GONE

        itineraryDetailResult()

    }

    private fun itineraryDetailResult() {
        val placeName = arguments?.getString("place_name")
        if (placeName != null) {
            viewModel.getItineraryByQuery(placeName)
        }
        Log.d(TAG, "itineraryDetailResult: $placeName")
        viewModel.itineraryDetailResult.observe(viewLifecycleOwner) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    binding.tvTitlePlaceToday.text = it.data!!.data[0].placeName
                    binding.tvDescriptionPlaceToday.text = it.data.data[0].description
                    binding.tvBudgetPlaceToday.text = it.data.data[0].price.toString()
                    binding.tvRatePlaceToday.text = it.data.data[0].rating.toString()
                    binding.tvLocationPlaceToday.text = it.data.data[0].province
                    binding.ivLocationPlace.setOnClickListener { _ ->
                        val lat = it.data.data[0].lat.toString()
                        val lon = it.data.data[0].long.toString()
//                        val intentUri = "https://www.google.com/maps?q=$lat,$lon"
                        val intentUri = "geo:$lat,$lon"
                        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(intentUri))
                        mapIntent.setPackage("com.google.android.apps.maps")
                        startActivity(mapIntent)
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
}