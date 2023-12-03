package com.itinergo.ui.register

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.itinergo.R
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.ActivityRegisterBinding
import com.itinergo.databinding.ActivitySplashscreenBinding
import com.itinergo.ui.home.HomeActivity
import com.itinergo.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        setEditText()
        setButton()
        registerResult()

    }

    private fun setButton() {
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsernameRegister.text.toString()
            val name = binding.etNameRegister.text.toString()
            val email = binding.etEmailRegister.text.toString()
            val password = binding.etPassword.text.toString()

            registerViewModel.registerUser(
                username = username,
                name = name,
                email = email,
                password = password
            )
        }
        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun registerResult() {
        registerViewModel.registerResult.observe(this) {
            when (it) {

                is BaseResponse.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is BaseResponse.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Success")
                    builder.setMessage(it.data?.message)

                    builder.setPositiveButton("OK") { _, _ ->
                        val intent = Intent(this, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    val dialog = builder.create()
                    dialog.show()
                }

                is BaseResponse.Error -> {
                    binding.progressBar.visibility = View.GONE
                    val builder = AlertDialog.Builder(this)
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


    private fun setEditText() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etEmailRegister.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etUsernameRegister.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setButtonText()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etNameRegister.addTextChangedListener(object : TextWatcher {
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
        binding.btnRegister.isEnabled =
            binding.etUsernameRegister.text != null && binding.etNameRegister.text != null && binding.etEmailRegister.text != null && binding.etPassword.text != null && binding.etUsernameRegister.text.toString()
                .isNotEmpty() && binding.etNameRegister.text.toString()
                .isNotEmpty() && binding.etEmailRegister.text.toString()
                .isNotEmpty() && binding.etPassword.text.toString().isNotEmpty()
    }

}