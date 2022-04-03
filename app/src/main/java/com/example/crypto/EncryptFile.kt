package com.example.crypto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.button.MaterialButton

class EncryptFile : AppCompatActivity() {

    private lateinit var imageView: ImageView

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encyprt_file)

        val btnSelectFile = findViewById<MaterialButton>(R.id.btnbrowse)
        val btnEncrypt = findViewById<MaterialButton>(R.id.btnencryption)
        imageView = findViewById(R.id.imageView)

        btnSelectFile.setOnClickListener {
            pickImageGallery()

        }

    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageURI(data?.data)
        }
    }
}

