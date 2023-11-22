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

class PlanFragment : BottomSheetDialogFragment(), View.OnClickListener {
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private var isAdded: Boolean = false
    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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

    private fun clickListeners() {
        binding.btn.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn -> if (isAdded) {
                val initialDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                binding.btn.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    initialDrawable,
                    null
                )
            } else {
                val newDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                binding.btn.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    newDrawable,
                    null
                )
            }

            R.id.btn2 -> if (isAdded) {
                val initialDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                binding.btn2.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    initialDrawable,
                    null
                )
            } else {
                val newDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                binding.btn2.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    newDrawable,
                    null
                )
            }

            R.id.btn3 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn3.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn3.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }

            R.id.btn4 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn4.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn4.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }

            R.id.btn5 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn5.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn5.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }

            R.id.btn6 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn6.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn6.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }

            R.id.btn7 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn7.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn7.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }

            R.id.btn8 ->
                if (isAdded) {
                    val initialDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_add_24)
                    binding.btn8.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        initialDrawable,
                        null
                    )
                } else {
                    val newDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_check_24)
                    binding.btn8.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        newDrawable,
                        null
                    )
                }
        }
        isAdded = !isAdded
    }
}