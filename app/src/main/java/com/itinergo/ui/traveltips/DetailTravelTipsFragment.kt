package com.itinergo.ui.traveltips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentDetailTravelTipsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTravelTipsFragment : Fragment() {
    private var _binding : FragmentDetailTravelTipsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TravelTipsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelTipsViewModel::class.java]
        _binding = FragmentDetailTravelTipsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE

        detailTipsResult()

    }

    private fun detailTipsResult() {
        val id = arguments?.getString("id")
        if (id != null) {
            viewModel.getDetailTravelTips(id)
        }
        viewModel.detailTravelTipsResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    Glide.with(requireContext()).load(it.data?.data?.image1).into(binding.imgPlaceTodayDetail)
                    binding.tvTitleTravelTips.text = it.data?.data?.title
                    binding.tvAuthorTravelTips.text = it.data?.data?.author
                    binding.tvContentTravelTips.text = it.data?.data?.contents

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

}