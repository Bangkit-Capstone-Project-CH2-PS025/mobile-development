package com.itinergo.ui.findtrip

import android.app.AlertDialog
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
import com.itinergo.adapter.AllTripAdapter
import com.itinergo.adapter.AllTripByIdAdapter
import com.itinergo.adapter.PlaceAdapter
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentFindTripBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindTripFragment : Fragment(),
    AllTripAdapter.ListPlaceInterface, AllTripByIdAdapter.ListPlaceInterface {
    private var _binding: FragmentFindTripBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FindTripViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[FindTripViewModel::class.java]
        _binding = FragmentFindTripBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.GONE
        setButton()
        allTripResult()
        allTripByIdResult()
    }


    private fun allTripResult() {
        viewModel.getAllTrip()
        viewModel.allTripResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.data?.data?.size != 0) {
                        binding.rvFtrip.visibility = View.VISIBLE
                        binding.tvNotFound4.visibility = View.GONE
                        val adapter = AllTripAdapter(this)
                        binding.rvFtrip.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvFtrip.adapter = adapter
                        adapter.setData(it.data!!.data)
                    } else {
                        binding.rvFtrip.visibility = View.GONE
                        binding.tvNotFound4.visibility = View.VISIBLE
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

    private fun allTripByIdResult() {
        viewModel.getAllTripById()
        viewModel.allTripByIdResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
//                    val adapter = AllTripByIdAdapter(this)
//                    binding.rvYtrip.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//                    binding.rvYtrip.adapter = adapter
//                    adapter.setData(it.data!!.data)

                    if (it.data?.data?.size != 0) {
                        binding.rvYtrip.visibility = View.VISIBLE
                        binding.tvNotFound3.visibility = View.GONE
                        val adapter = AllTripByIdAdapter(this)
                        binding.rvYtrip.layoutManager = LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                        binding.rvYtrip.adapter = adapter
                        adapter.setData(it.data!!.data)
                    } else {
                        binding.rvYtrip.visibility = View.GONE
                        binding.tvNotFound3.visibility = View.VISIBLE
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
        binding.ivAddListFtrip.setOnClickListener {
            findNavController().navigate(R.id.action_findTripFragment_to_addTripFragment)
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun trip(contact: String) {
        val bundle = Bundle()
        bundle.putString("contact", contact)
        findNavController().navigate(R.id.action_findTripFragment_to_voilaFtripFragment, bundle)
    }

    override fun yourTrip(id: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Trip")
        builder.setMessage("Are you sure to delete this trip?")

        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteTrip(id)
            deleteResult()
            findNavController().navigate(R.id.findTripFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteResult() {
        viewModel.deleteTripResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Success: ${it.data?.message}", Toast.LENGTH_SHORT).show()
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

    override fun onResume() {
        super.onResume()
        viewModel.getAllTripById()
        viewModel.getAllTrip()
    }
}