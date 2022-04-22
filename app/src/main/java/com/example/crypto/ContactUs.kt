package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class ContactUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        val firstName = findViewById<TextView>(R.id.firstName)
        val lastName = findViewById<TextView>(R.id.lastName)
        val email = findViewById<TextView>(R.id.email)
        val message = findViewById<TextView>(R.id.message)


        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            if (firstName.text.toString().isEmpty() || lastName.text.toString()
                    .isEmpty() || email.text.toString().isEmpty() || message.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Your message received!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}