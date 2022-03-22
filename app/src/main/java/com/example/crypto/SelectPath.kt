package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class SelectPath : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_path)

        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        val btnAboutUs = findViewById<MaterialButton>(R.id.btnabout)

        // logout

        btnLogout.setOnClickListener {
            var Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
        }

        // about us

        btnAboutUs.setOnClickListener {
            var Intent = Intent(this, AboutUs::class.java)
            startActivity(Intent)
        }


    }
}