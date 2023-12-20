package com.itinergo.ui.travelbudgeting

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentAddBudgetingBinding
import com.itinergo.databinding.FragmentDetailBudgetingBinding
import com.itinergo.databinding.FragmentTravelBudgetingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBudgetingFragment : Fragment() {
    private var _binding : FragmentDetailBudgetingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : TravelBudgetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelBudgetingViewModel::class.java]
        _binding = FragmentDetailBudgetingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setResult()
    }

    @SuppressLint("SetTextI18n")
    private fun setResult() {
        val id = arguments?.getString("id")
        if (id != null) {
            viewModel.getTravelBudgetById(id)
        }
        viewModel.travelBudgetById.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvBudgetName.text = it.data?.data?.budgetName
                    binding.tvTargetBudget.text = "Rp${it.data?.data?.target.toString()}"
                    binding.tvAttraction.text = "Rp${it.data?.data?.attractions.toString()}"
                    binding.tvFlight.text = "Rp${it.data?.data?.flight.toString()}"
                    binding.tvFood.text = "Rp${it.data?.data?.food.toString()}"
                    binding.tvShopping.text = "Rp${it.data?.data?.shopping.toString()}"
                    binding.tvStay.text = "Rp${it.data?.data?.stay.toString()}"
                    binding.tvOthers.text = "Rp${it.data?.data?.others.toString()}"
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