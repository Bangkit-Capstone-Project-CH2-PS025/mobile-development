package com.itinergo.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.itinergo.R
import com.itinergo.databinding.FragmentPlanBinding
import com.itinergo.utils.RecomendedButton

class PlanFragment : BottomSheetDialogFragment(), View.OnClickListener {
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

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

        for (i in 0..<buttons.size) {
            val button = buttons[i]
            button.setOnClickListener {
                // default behaviour
                if (buttonChecked(i)) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
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
                            R.drawable.baseline_check_24
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
            }
        }
    }
    private fun setIcon() {
        val initialDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
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