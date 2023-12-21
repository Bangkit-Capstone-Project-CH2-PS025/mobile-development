package com.itinergo.ui.savedplace

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentDetailSavedPlaceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailSavedPlaceFragment : Fragment() {

    private var _binding: FragmentDetailSavedPlaceBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SavedPlaceViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[SavedPlaceViewModel::class.java]
        _binding = FragmentDetailSavedPlaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButton()
        setDetailResult()
        setFinishResult()
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
    }

    private fun setFinishResult() {
        viewModel.updateSavedPlaceResult.observe(viewLifecycleOwner){
            when(it){
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_detailSavedPlaceFragment_to_voilaSavedPlaceFragment)
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
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailResult() {
        val id = arguments?.getString("id")
        if (id != null) {
            viewModel.getDetailSavedPlace(id)
        }
        viewModel.detailSavedPlaceResult.observe(viewLifecycleOwner) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    val budget = it.data?.data?.budget
                    val city = it.data?.data?.city?.uppercase()
                    binding.tvBudgetDetailSaved.text = "Rp$budget"
                    binding.tvTripNameSavedDetail.text = "$city TRIP"
                    binding.back.setOnClickListener {
                        findNavController().popBackStack()
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest1.let { placeName ->
                        binding.tvDay11.text = placeName
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest2.let { placeName ->
                        binding.tvDay12.text = placeName
                    }

                    it.data?.data?.detailPlans?.get(0)?.dest3.let { placeName ->
                        binding.tvDay13.text = placeName
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest4.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay21.text = placeName
                        }
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest5.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay22.text = placeName
                        }
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest6.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay23.text = placeName
                        }
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest7.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay21.text = placeName
                        }
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest8.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay21.text = placeName
                        }
                    }
                    it.data?.data?.detailPlans?.get(0)?.dest9.let { placeName ->
                        if (placeName != null) {
                            binding.tvDay21.text = placeName
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
        binding.btnFinishDetail.setOnClickListener {
            val id = arguments?.getString("id")
            if (id != null) {
                viewModel.updateFinishSaved(id)
            }
        }
    }

}