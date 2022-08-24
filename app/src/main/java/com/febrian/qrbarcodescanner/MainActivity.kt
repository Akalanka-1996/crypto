package com.febrian.qrbarcodescanner

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import com.febrian.qrbarcodescanner.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.properties.Delegates

const val BASE_URL = "https://androidbarcodereader.herokuapp.com/api/"

class MainActivity : AppCompatActivity() {

    companion object {
        const val RESULT = "RESULT"
    }

    private lateinit var binding: ActivityMainBinding
    var myVariable by Delegates.notNull<Long>()

    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTextScan.setOnClickListener{
            var Intent = Intent(this, TextDetect::class.java)
            startActivity(Intent)
        }

        binding.btnScan.setOnClickListener {
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivity(intent)
        }

        val result = intent.getStringExtra(RESULT)

        if (result != null) {
            if (result.contains("https://") || result.contains("http://")) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result))
                startActivity(intent)
            } else {
                binding.result.text = result.toString()
//                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                myVariable = result.toLong()

            }
        }

        if (result != null) {
            getMyData()
        }

    }


    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(myVariable)

        retrofitData.enqueue(object : Callback<QrDataItem?> {
            override fun onResponse(call: Call<QrDataItem?>, response: Response<QrDataItem?>) {
                if(response.isSuccessful) {
                    val productName = response.body()?.name.toString()
                    binding.name.text = productName.toString()

                    tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
                        if(it == TextToSpeech.SUCCESS) {
                            tts.language = Locale.US
                            tts.setSpeechRate(1.0f)
                            tts.speak(productName.toString(), TextToSpeech.QUEUE_ADD, null)
                        }
                    })
                }
            }

            override fun onFailure(call: Call<QrDataItem?>, t: Throwable) {

            }
        })

    }



}