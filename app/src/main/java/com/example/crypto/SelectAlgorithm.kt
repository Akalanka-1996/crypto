package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton

class SelectAlgorithm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_algorithm)

        val btnBlowfish = findViewById<MaterialButton>(R.id.btnblowfish)
        val btnAES = findViewById<MaterialButton>(R.id.btnaes)
        val btnDES = findViewById<MaterialButton>(R.id.btndes)

        // blowfish

        btnBlowfish.setOnClickListener {
            Toast.makeText(this, "Blowfish algorithm selected", Toast.LENGTH_SHORT).show()
            var Intent = Intent(this, SelectFile::class.java)
            startActivity(Intent)
        }

        // AES

        btnAES.setOnClickListener {
            Toast.makeText(this, "AES algorithm selected", Toast.LENGTH_SHORT).show()
            var Intent = Intent(this, SelectFile::class.java)
            startActivity(Intent)
        }

        // DES

        btnDES.setOnClickListener {
            Toast.makeText(this, "DES algorithm selected", Toast.LENGTH_SHORT).show()
            var Intent = Intent(this, SelectFile::class.java)
            startActivity(Intent)
        }


    }
}