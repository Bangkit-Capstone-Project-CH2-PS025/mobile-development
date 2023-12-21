package com.itinergo.ui.travelbudgeting

import android.app.AlertDialog
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
import com.itinergo.databinding.FragmentEditTravelBudgetingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTravelBudgetingFragment : Fragment() {
    private var _binding: FragmentEditTravelBudgetingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TravelBudgetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelBudgetingViewModel::class.java]
        _binding = FragmentEditTravelBudgetingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE

        setResult()
        setButton()
    }

    private fun setButton() {
        binding.btnUpdateEditBudget.setOnClickListener {
            val flight = binding.etFlightEdit.text.toString()
            val attractions = binding.etAttractionsEdit.text.toString()
            val food = binding.etFoodEdit.text.toString()
            val others = binding.etOthersEdit.text.toString()
            val stay = binding.etStayEdit.text.toString()
            val shopping = binding.etShoppingEdit.text.toString()

            val id = arguments?.getString("id")
            if (id != null) {
                viewModel.updateBudgeting(
                    flight,
                    attractions,
                    shopping,
                    food,
                    stay,
                    others,
                    id
                )
            }
            createResult()
        }
    }

    private fun createResult() {
        viewModel.updateBudgetingResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Success")
                    builder.setMessage(it.data?.message)

                    builder.setPositiveButton("OK") { _, _ ->
                        val bundle = Bundle()
                        val id = arguments?.getString("id")
                        bundle.putString("id", id)
                        findNavController().navigate(R.id.action_editTravelBudgetingFragment_to_detailBudgetingFragment, bundle)
                    }
                    val dialog = builder.create()
                    dialog.show()
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

    private fun setResult() {
        val id = arguments?.getString("id")
        Log.d(TAG, "setResult: $id")
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
                    binding.etFlightEdit.setText(it.data?.data?.flight.toString())
                    binding.etAttractionsEdit.setText(it.data?.data?.attractions.toString())
                    binding.etFoodEdit.setText(it.data?.data?.food.toString())
                    binding.etShoppingEdit.setText(it.data?.data?.shopping.toString())
                    binding.etStayEdit.setText(it.data?.data?.stay.toString())
                    binding.etOthersEdit.setText(it.data?.data?.others.toString())

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