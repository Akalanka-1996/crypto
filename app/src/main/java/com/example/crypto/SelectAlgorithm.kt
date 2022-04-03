package com.example.crypto

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup

class SelectAlgorithm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_algorithm)

        val toggleButtonGroup = findViewById<MaterialButtonToggleGroup>(R.id.toggleButtonGroup)
        val intentValue = intent.getStringExtra("type")


        toggleButtonGroup.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btntext -> {
                        showToast("Text selected")
                        selectTextAlgorithm()
                    }
                    R.id.btnfile -> {
                        showToast("File selected.")
                        selectFileAlgorithm()

                    }
                }
            } else {
                if (toggleButtonGroup.checkedButtonId == View.NO_ID) {
                    showToast("Nothing Selected")
                }
            }
        }


    }

    // toggle button 'text'

    private fun selectTextAlgorithm() {
        val btnBlowfish = findViewById<MaterialButton>(R.id.btnblowfish)
        val btnAES = findViewById<MaterialButton>(R.id.btnaes)
        val btnDES = findViewById<MaterialButton>(R.id.btndes)


        //  Blowfish

        btnBlowfish.setOnClickListener {
            Toast.makeText(this, "Blowfish algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptText::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptText::class.java)
                startActivity(Intent)
            }


        }

        // AES

        btnAES.setOnClickListener {
            Toast.makeText(this, "AES algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptText::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptText::class.java)
                startActivity(Intent)
            }
        }

        // DES

        btnDES.setOnClickListener {
            Toast.makeText(this, "DES algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptText::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptText::class.java)
                startActivity(Intent)
            }
        }
    }

    // toggle button 'file'

    private fun selectFileAlgorithm() {
        val btnBlowfish = findViewById<MaterialButton>(R.id.btnblowfish)
        val btnAES = findViewById<MaterialButton>(R.id.btnaes)
        val btnDES = findViewById<MaterialButton>(R.id.btndes)

        //  Blowfish

        btnBlowfish.setOnClickListener {
            Toast.makeText(this, "Blowfish algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptFile::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptFile::class.java)
                startActivity(Intent)
            }
        }

        // AES

        btnAES.setOnClickListener {
            Toast.makeText(this, "AES algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptFile::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptFile::class.java)
                startActivity(Intent)
            }
        }

        // DES

        btnDES.setOnClickListener {
            Toast.makeText(this, "DES algorithm selected", Toast.LENGTH_SHORT).show()
            val type = intent.getStringExtra("type")
            if (type.equals("encrypt")) {
                var Intent = Intent(this, EncryptFile::class.java)
                startActivity(Intent)
            }
            if (type.equals("decrypt")) {
                var Intent = Intent(this, DecryptFile::class.java)
                startActivity(Intent)
            }
        }
    }

    private fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }


}