package com.ashwin.android.tmdbuitest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ashwin.android.tmdbuitest.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
