package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = findViewById<TextView>(R.id.username);
        val password = findViewById<TextView>(R.id.password);

        val btnLogin = findViewById<MaterialButton>(R.id.btnlogin);
        val btnSignUP = findViewById<MaterialButton>(R.id.btnsignup)

        // admin and admin

        btnLogin.setOnClickListener {
           if (username.text.toString().equals("admin") && password.text.toString().equals("admin")) {
               // correct
               Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show()
           } else {
               // incorrect
               Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()

           }
        }

        // move to signup screen

        btnSignUP.setOnClickListener {
            val Intent = Intent(this, SignUP::class.java)
            startActivity(Intent)
        }



    }
}