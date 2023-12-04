package com.itinergo.ui.login

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.itinergo.data.response.BaseResponse
import com.itinergo.databinding.ActivityLoginBinding
import com.itinergo.ui.home.HomeActivity
import com.itinergo.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setEditText()
        setButton()
        loginResult()
    }

    private fun setButton() {
        binding.btnLogin.setOnClickListener {
            val usernameOrEmail = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            loginViewModel.loginUser(usernameOrEmail = usernameOrEmail, password = password)
        }
        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginResult() {
        loginViewModel.loginResult.observe(this) {
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
                        loginViewModel.saveIsLoginStatus(true)
                        loginViewModel.saveToken(it.data?.token.toString())

                        val intent = Intent(this, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
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

                setButtonText()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etEmail.addTextChangedListener(object : TextWatcher {
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
        binding.btnLogin.isEnabled =
            binding.etEmail.text != null && binding.etPassword.text != null && binding.etEmail.text.toString()
                .isNotEmpty() && binding.etPassword.text.toString().isNotEmpty()
    }
}