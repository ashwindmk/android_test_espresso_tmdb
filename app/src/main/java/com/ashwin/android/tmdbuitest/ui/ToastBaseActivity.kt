package com.ashwin.android.tmdbuitest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.databinding.ActivityToastBaseBinding

class ToastBaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityToastBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToastBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showToastButton.setOnClickListener {
            showToast(getString(R.string.message))
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
