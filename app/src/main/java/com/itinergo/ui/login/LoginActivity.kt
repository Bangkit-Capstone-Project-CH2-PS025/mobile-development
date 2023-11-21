package com.itinergo.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.itinergo.R
import com.itinergo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                setButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setButton() {
        binding.btnLogin.isEnabled =
            binding.etEmail.text != null && binding.etPassword.text != null && binding.etEmail.text.toString()
                .isNotEmpty() && binding.etPassword.text.toString().isNotEmpty()
    }
}