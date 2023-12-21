package com.itinergo.ui.travelbudgeting

import android.app.AlertDialog
import android.os.Bundle
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
import com.itinergo.databinding.FragmentAddBudgetingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBudgetingFragment : Fragment() {
    private var _binding: FragmentAddBudgetingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : TravelBudgetingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[TravelBudgetingViewModel::class.java]
        _binding = FragmentAddBudgetingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE

        setButton()
    }

    private fun setButton() {
        binding.btnUploadAddBudgeting.setOnClickListener {
            if (binding.etBudgetNameAdd.text.toString().isNotEmpty() &&
                binding.etFlightAdd.text.toString().isNotEmpty() &&
                binding.etTargetAdd.text.toString().isNotEmpty() &&
                binding.etAttractionsAdd.text.toString().isNotEmpty() &&
                binding.etFoodAdd.text.toString().isNotEmpty() &&
                binding.etOthersAdd.text.toString().isNotEmpty() &&
                binding.etStayAdd.text.toString().isNotEmpty()
            ) {
                val budgetName = binding.etBudgetNameAdd.text.toString()
                val target = binding.etTargetAdd.text.toString()
                val flight = binding.etFlightAdd.text.toString()
                val attractions = binding.etAttractionsAdd.text.toString()
                val food = binding.etFoodAdd.text.toString()
                val others = binding.etOthersAdd.text.toString()
                val stay = binding.etStayAdd.text.toString()
                val shopping = binding.etShoppingAdd.text.toString()

                viewModel.createBudgeting(budgetName,target, flight, attractions, shopping, food, stay, others)
                createResult()
            } else {
                Toast.makeText(requireContext(), "Fill all the input first!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun createResult() {
        viewModel.createBudgetingResult.observe(viewLifecycleOwner) {
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
                        findNavController().navigate(R.id.action_addBudgetingFragment_to_voilaBudgetFragment)
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
}