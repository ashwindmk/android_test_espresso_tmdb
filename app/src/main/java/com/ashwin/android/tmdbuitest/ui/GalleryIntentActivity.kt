package com.ashwin.android.tmdbuitest.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.ashwin.android.tmdbuitest.databinding.ActivityGalleryIntentBinding
import com.bumptech.glide.Glide

const val GALLERY_REQUEST_CODE = 1234
private const val TAG = "GalleryIntentActivity"

class GalleryIntentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGalleryIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openGalleryButton.setOnClickListener {
            pickFromGallery()
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "RESULT_OK")
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    Log.d(TAG, "GALLERY_REQUEST_CODE detected.")
                    data?.data?.let { uri ->
                        Log.d(TAG, "URI: $uri")
                        Glide.with(this)
                            .load(uri)
                            .into(binding.imageView)
                    }
                }
            }
        }
    }
}
