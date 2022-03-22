package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton

class AboutUs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_about_us)

        val btnBack = findViewById<MaterialButton>(R.id.btnback)

        // go back

        btnBack.setOnClickListener {
            var Intent = Intent(this, SelectPath::class.java)
            startActivity(Intent)
        }


    }
}

