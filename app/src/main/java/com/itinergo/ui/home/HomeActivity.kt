package com.itinergo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.itinergo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val dialogFragment = PlanFragment()
            dialogFragment.show(supportFragmentManager, "bottomsheet")
        }
    }
}