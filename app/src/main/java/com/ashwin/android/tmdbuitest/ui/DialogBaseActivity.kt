package com.ashwin.android.tmdbuitest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.databinding.ActivityDialogBaseBinding

class DialogBaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDialogBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchDialogButton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val input = EditText(this)
        input.id = R.id.dialog_edit_text  // For Espresso to find withId.
        input.hint = "Enter Text"
        input.inputType = InputType.TYPE_CLASS_TEXT

        val dialog = AlertDialog.Builder(this)
            .setTitle("Dialog One")
            .setMessage(R.string.enter_name)
            .setView(input)
            .setPositiveButton(R.string.ok, null)  // Override click listener below
            .setCancelable(false)
            .show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // This will prevent dialog from dismissing on positive button click
            val name = input.text.toString()
            if (name.isNotBlank()) {
                setName(name)
                dialog.dismiss()
            }
        }
    }

    private fun setName(name: String) {
        binding.nameTextView.text = name
    }
}
