package com.example.lesson_5_rudomanov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_5_rudomanov.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySecondBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding.buttonTo3.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}