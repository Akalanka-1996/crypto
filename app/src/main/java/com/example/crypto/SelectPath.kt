package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class SelectPath : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_path)

        val btnEncryption = findViewById<MaterialButton>(R.id.btnencryption)
        val btnDecryption = findViewById<MaterialButton>(R.id.btndecryption)
        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        val btnAboutUs = findViewById<MaterialButton>(R.id.btnabout)

        // go to encryption

        btnEncryption.setOnClickListener {
            var intent = Intent(this, SelectAlgorithm::class.java)
            val type = String()
            intent.putExtra("type","encrypt")
            startActivity(intent)
        }

        // go to decryption

        btnDecryption.setOnClickListener {
            var intent = Intent(this, SelectAlgorithm::class.java)
            val type = String()
            intent.putExtra("type", "decrypt")
            startActivity(intent)
        }
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