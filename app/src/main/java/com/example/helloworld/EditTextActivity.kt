package com.example.helloworld

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.net.Uri
import androidx.core.net.toUri
import android.widget.Toast

class EditTextActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "EditTextActivityTag"
    }

    fun validateNumber(text: String, ): String {
        val filtered_text = text
            .replace("+7", "8")
            .filter {it.isDigit()}
        return filtered_text
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_text)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val phone = findViewById<EditText>(R.id.phone_number_input)

        val open_button: Button = findViewById<Button>(R.id.button_to_second_activity)
        open_button.setOnClickListener{
            Log.i(TAG, "send text ${phone.text} to second activity")

            val filtered_text = validateNumber(phone.text.toString())
            val intent = Intent(this, PhoneActivity::class.java).putExtra("phone", filtered_text)

            startActivity(intent)
        }

        val call_button: Button = findViewById<Button>(R.id.call_button)
        call_button.setOnClickListener{
            Log.i(TAG, "phone number is ${phone.text}")

            var toShow: Boolean = true

            val filtered_text = validateNumber(phone.text.toString())
            if(filtered_text.isEmpty()) {
                Toast.makeText(
                    this.baseContext,
                    "Invalid number entered. Please try again",
                    Toast.LENGTH_SHORT
                ).show()
                toShow = false
            }

            val intent = Intent(Intent.ACTION_DIAL, ("tel:" + filtered_text).toUri())

            if (intent.resolveActivity(packageManager) != null && toShow) {
                startActivity(intent)
            }
        }
    }
}