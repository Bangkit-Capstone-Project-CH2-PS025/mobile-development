package com.itinergo.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itinergo.R
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.databinding.FragmentPlanBinding
import com.itinergo.utils.RecomendedButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    // max selected button is 2
    private var clickedButtonIndex = arrayListOf<Int>()
    private var buttons = arrayListOf<RecomendedButton>()

    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setIcon()
        clickListeners()

    }

    private fun addButtonChecked(index: Int) {
        clickedButtonIndex.add(index)

        if (clickedButtonIndex.size >= 2) {
            for (i in 0..<buttons.size) {
                val button = buttons[i]

                // disable all the button that is not in index
                if (!buttonChecked(i)) {
                    button.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_add_recomend_button_disabled
                    )
                    button.isEnabled = false
                }
            }
        }

    }

    private fun buttonChecked(index: Int): Boolean {
        for (i in 0..<clickedButtonIndex.size) {
            if (index == clickedButtonIndex[i]) {
                return true
            }
        }
        return false
    }

    private fun removeButtonChecked(index: Int) {
        var newClickedButtonIndex = arrayListOf<Int>()
        for (i in 0..<clickedButtonIndex.size - 1) {
            if (index != clickedButtonIndex[i]) {
                newClickedButtonIndex.add(clickedButtonIndex[i])
            }
        }

        clickedButtonIndex = newClickedButtonIndex
        if (clickedButtonIndex.size < 2) {
            for (i in 0..<buttons.size) {
                val button = buttons[i]

                // enable all the button that is not in index
                if (!buttonChecked(i)) {
                    button.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_add_recomend_button_disabled
                    )
                    button.isEnabled = true
                }
            }
        }
    }

    private fun clickListeners() {
        buttons.add(binding.btn)
        buttons.add(binding.btn2)
        buttons.add(binding.btn3)
        buttons.add(binding.btn4)
        buttons.add(binding.btn5)
        buttons.add(binding.btn6)
        buttons.add(binding.btn7)
        buttons.add(binding.btn8)


        val cityButton = arguments?.getString("city")
        if (cityButton != null) {
            viewModel.postPreferences(cityButton)
        }
        viewModel.postPreferencesResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE

                    it.data?.data?.indices?.forEach { index ->
                        buttons.getOrNull(index)?.text = it.data.data.getOrNull(index) ?: ""
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

        for (i in 0..<buttons.size) {
            val button = buttons[i]
            button.setOnClickListener {
                // default behaviour
                if (buttonChecked(i)) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
                    button.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                    button.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_add_recomend_button
                    )

                    removeButtonChecked(i)
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_check
                        )
                    button.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                    button.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.bg_unadd_recomend_button
                    )
                    button.isEnabled = true

                    addButtonChecked(i)
                }
                binding.btnPlan.setOnClickListener {
                    // Ambil teks dari dua tombol yang dipilih
                    val selectedTexts = getSelectedButtonTexts()
                    if (selectedTexts.size == 2) {
                        val firstPreferences = selectedTexts[0]
                        val secondPreferences = selectedTexts[1]
                        val city = arguments?.getString("city")
                        val budget = arguments?.getString("budget")
                        val duration = arguments?.getString("duration")

                        val bundle = Bundle()
                        bundle.putString("first_preferences", firstPreferences)
                        bundle.putString("second_preferences", secondPreferences)
                        bundle.putString("city", city)
                        bundle.putString("budget", budget)
                        bundle.putString("duration", duration)
                        findNavController().navigate(R.id.action_planFragment_to_navigation_home,bundle)
                    }
                }
            }
        }
    }
    private fun getSelectedButtonTexts(): List<String> {
        val selectedTexts = mutableListOf<String>()
        for (index in clickedButtonIndex) {
            buttons.getOrNull(index)?.let {
                selectedTexts.add(it.text.toString())
            }
        }
        return selectedTexts
    }
    private fun setIcon() {
        val initialDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
        binding.btn.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn2.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn3.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn4.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn5.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn6.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn7.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
        binding.btn8.setCompoundDrawablesWithIntrinsicBounds(null, null, initialDrawable, null)
    }

    // LEGACY
//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.btn -> if (isAdded) {
//                val initialDrawable =
//                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                binding.btn.setCompoundDrawablesWithIntrinsicBounds(
//                    null,
//                    null,
//                    initialDrawable,
//                    null
//                )
//                binding.btn.background =
//                    ContextCompat.getDrawable(
//                        requireContext(),
//                        R.drawable.bg_add_recomend_button
//                    )
//            } else {
//                val newDrawable =
//                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
//                binding.btn.setCompoundDrawablesWithIntrinsicBounds(
//                    null,
//                    null,
//                    newDrawable,
//                    null
//                )
//            }
//
//            R.id.btn2 -> if (isAdded) {
//                val initialDrawable =
//                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                binding.btn2.setCompoundDrawablesWithIntrinsicBounds(
//                    null,
//                    null,
//                    initialDrawable,
//                    null
//                )
//                binding.btn2.background =
//                    ContextCompat.getDrawable(
//                        requireContext(),
//                        R.drawable.bg_add_recomend_button
//                    )
//            } else {
//                val newDrawable =
//                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
//                binding.btn2.setCompoundDrawablesWithIntrinsicBounds(
//                    null,
//                    null,
//                    newDrawable,
//                    null
//                )
//            }
//
//            R.id.btn3 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn3.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn3.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//
//            R.id.btn4 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn4.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn4.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//
//            R.id.btn5 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn5.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn5.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//
//            R.id.btn6 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn6.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn6.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//
//            R.id.btn7 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn7.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn7.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//
//            R.id.btn8 ->
//                if (isAdded) {
//                    val initialDrawable =
//                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
//                    binding.btn8.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        initialDrawable,
//                        null
//                    )
//                } else {
//                    val newDrawable =
//                        ContextCompat.getDrawable(
//                            requireContext(),
//                            R.drawable.baseline_check_24
//                        )
//                    binding.btn8.setCompoundDrawablesWithIntrinsicBounds(
//                        null,
//                        null,
//                        newDrawable,
//                        null
//                    )
//                }
//        }
//        isAdded = !isAdded
//    }
}