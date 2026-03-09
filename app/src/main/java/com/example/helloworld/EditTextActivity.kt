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

class EditTextActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "EditTextActivityTag"
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
            Log.i("EditTextActivityTag", "send text ${phone.text} to second activity")

            val intent = Intent(this, PhoneActivity::class.java).putExtra("phone", phone.text)

            startActivity(intent)
        }


    }
}