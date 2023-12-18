package com.itinergo.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.postitinerary.DataDay
import com.itinergo.data.response.postitinerary.DataX
import com.itinergo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navbar = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navbar?.visibility = View.VISIBLE


        setButton()
        setDropdown()
        setText()
    }

    private fun setDropdown() {
        val city = resources.getStringArray(R.array.city)
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, city)
        binding.etCity.setAdapter(adapter)

        val duration = resources.getStringArray(R.array.duration)
        val durationAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, duration)
        binding.etDuration.setAdapter(durationAdapter)
    }

    private fun postItineraryResult() {
        viewModel.postItineraryResult.observe(viewLifecycleOwner) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val dataArrayList1 = ArrayList<DataDay>()
                    val dataArrayList2 = ArrayList<DataDay>()
                    val dataArrayList3 = ArrayList<DataDay>()
                    dataArrayList1.add(
                        DataDay(
                            it.data?.data?.day1?.get(0)?.id,
                            it.data?.data?.day1?.get(0)?.placeName
                        )
                    )
                    dataArrayList1.add(
                        DataDay(
                            it.data?.data?.day1?.get(1)?.id,
                            it.data?.data?.day1?.get(1)?.placeName
                        )
                    )
                    dataArrayList1.add(
                        DataDay(
                            it.data?.data?.day1?.get(2)?.id,
                            it.data?.data?.day1?.get(2)?.placeName
                        )
                    )
                    dataArrayList2.add(
                        DataDay(
                            it.data?.data?.day2?.get(0)?.id,
                            it.data?.data?.day2?.get(0)?.placeName
                        )
                    )
                    dataArrayList2.add(
                        DataDay(
                            it.data?.data?.day2?.get(1)?.id,
                            it.data?.data?.day2?.get(1)?.placeName
                        )
                    )
                    dataArrayList2.add(
                        DataDay(
                            it.data?.data?.day2?.get(2)?.id,
                            it.data?.data?.day2?.get(2)?.placeName
                        )
                    )
                    dataArrayList3.add(
                        DataDay(
                            it.data?.data?.day3?.get(0)?.id,
                            it.data?.data?.day3?.get(0)?.placeName
                        )
                    )
                    dataArrayList3.add(
                        DataDay(
                            it.data?.data?.day3?.get(1)?.id,
                            it.data?.data?.day3?.get(1)?.placeName
                        )
                    )
                    dataArrayList3.add(
                        DataDay(
                            it.data?.data?.day3?.get(2)?.id,
                            it.data?.data?.day3?.get(2)?.placeName
                        )
                    )
                    Log.d(
                        TAG,
                        "postItineraryResult: ${it.data?.data?.day1?.get(0)?.placeName.toString()}"
                    )
                    val bundle = Bundle()
                    val city = binding.etCity.text.toString()
                    val duration = binding.etDuration.text.toString()
                    val budget = binding.etBudget.text.toString()
                    var countPlace = ""
                    if (duration == "1") {
                        countPlace = "3"
                    } else if (duration == "2") {
                        countPlace = "6"
                    } else {
                        countPlace = "9"
                    }
                    bundle.putString("city", city)
                    bundle.putString("budget", budget)
                    bundle.putString("duration", duration)
                    bundle.putString("count_place", countPlace)
                    bundle.putParcelable(
                        "listDay",
                        DataX(dataArrayList1, dataArrayList2, dataArrayList3)
                    )
                    findNavController().navigate(
                        R.id.action_navigation_home_to_itineraryPlanningFragment2, bundle
                    )
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

    @SuppressLint("SetTextI18n")
    private fun setText() {
        val firstPreferences = arguments?.getString("first_preferences")
        val secondPreferences = arguments?.getString("second_preferences")
        val city = arguments?.getString("city")
        val budget = arguments?.getString("budget")
        val duration = arguments?.getString("duration")

        if (firstPreferences != null) {
            binding.etPreference.setText("$firstPreferences, $secondPreferences")
        }
        if (city != null) {
            binding.etCity.setText(city)
        }
        if (budget != null) {
            binding.etBudget.setText(budget)
        }
        if (duration != null) {
            binding.etDuration.setText(duration)
        }
        setDropdown()

        viewModel.getDataStoreName().observe(viewLifecycleOwner) {
            binding.tvUsernameIntro.text = "Hi, $it"
        }

    }

    private fun setButton() {
        binding.etPreference.setOnClickListener {
            val city = binding.etCity.text.toString()
            val budget = binding.etBudget.text.toString()
            val duration = binding.etDuration.text.toString()
            val bundle = Bundle()
            bundle.putString("city", city)
            bundle.putString("budget", budget)
            bundle.putString("duration", duration)
            findNavController().navigate(R.id.action_navigation_home_to_planFragment, bundle)
        }

        binding.ivViewHome.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_placeTodayFragment)
        }
        binding.btnSearch.setOnClickListener {
            if (arguments != null) {
                val firstPreferences = arguments?.getString("first_preferences")
                val secondPreferences = arguments?.getString("second_preferences")
                val city = binding.etCity.text.toString().lowercase(Locale.getDefault())
                val duration = binding.etDuration.text.toString().toInt()
                val budget = binding.etBudget.text.toString().toInt()
                val preferences1 = firstPreferences.toString().lowercase(Locale.getDefault())
                val preferences2 = secondPreferences.toString().lowercase(Locale.getDefault())

                if (binding.etCity.text.toString()
                        .isNotEmpty() && binding.etDuration.text.toString()
                        .isNotEmpty() && binding.etBudget.text.toString()
                        .isNotEmpty() && binding.etPreference.text.toString().isNotEmpty()
                ) {
                    viewModel.postItinerary(city, budget, duration, preferences1, preferences2)
                    postItineraryResult()
                }
            }
        }
        binding.ivFindTrip.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_findTripFragment)
        }
        binding.ivTravelTips.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_travelTipsFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}