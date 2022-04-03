package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val username = findViewById<TextView>(R.id.username)
        val password = findViewById<TextView>(R.id.password)
        val email = findViewById<TextView>(R.id.email)

        val btnSignup = findViewById<MaterialButton>(R.id.btnSignUpIn)

        btnSignup.setOnClickListener {
            if (username.text.toString().isEmpty() || password.text.toString()
                    .isEmpty() || email.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            } else {
                var Intent = Intent(this, SelectPath::class.java)
                startActivity(Intent)
            }
        }
    }
}