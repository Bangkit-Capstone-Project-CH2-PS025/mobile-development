package com.itinergo.ui.forgot

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.FragmentForgotPasswordBinding
import com.itinergo.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : DialogFragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ForgotPasswordViewModel
    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.apply {
                setLayout(
                    300.toPx(requireContext()),
                    400.toPx(requireContext())
                )
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        setEditText()
        setButton()
        forgotResult()

    }

    private fun forgotResult() {
        viewModel.forgotResult.observe(this) {
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
                        val intent = Intent(requireContext(), LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
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

    private fun setButton() {
        binding.btnSendForgot.setOnClickListener {
            val email = binding.edEmailForgot.text.toString()

            viewModel.forgotPassword(email)
        }
    }

    private fun setEditText() {
        binding.edEmailForgot.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setButtonText()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setButtonText() {
        binding.btnSendForgot.isEnabled =
            binding.edEmailForgot.text != null && binding.edEmailForgot.text.toString().isNotEmpty()
    }

}

private fun Int.toPx(requireContext: Context): Int =
    (this * requireContext.resources.displayMetrics.density).toInt()
