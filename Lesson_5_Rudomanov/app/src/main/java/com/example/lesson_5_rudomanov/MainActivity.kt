package com.example.lesson_5_rudomanov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_5_rudomanov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.buttonTo4.setOnClickListener {
            startActivity(
                ForthActivity.createStartIntent(
                    this, System.currentTimeMillis().toString()
                )
            )
        }

        binding.buttonTo2.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        binding.buttonTo6.setOnClickListener {
            val intent = Intent(this, SixthActivity::class.java)
            startActivity(intent)
        }
    }
}