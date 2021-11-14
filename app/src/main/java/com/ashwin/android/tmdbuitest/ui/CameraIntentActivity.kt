package com.ashwin.android.tmdbuitest.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.ashwin.android.tmdbuitest.databinding.ActivityCameraIntentBinding

const val REQUEST_IMAGE_CAPTURE = 1234
const val KEY_IMAGE_DATA = "data"

class CameraIntentActivity : AppCompatActivity() {
    private val TAG: String = CameraIntentActivity::class.java.simpleName

    private lateinit var binding: ActivityCameraIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openCameraButton.setOnClickListener {
            dispatchCameraIntent()
        }
    }

    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "RESULT_OK")
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    data?.extras.let{ extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        binding.imageView.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }
}
