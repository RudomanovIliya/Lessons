package com.example.lesson_5_rudomanov

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_5_rudomanov.databinding.ActivityThirdBinding
import com.google.android.material.snackbar.Snackbar

class ThirdActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityThirdBinding::bind)

    private val launcher = registerForActivityResult(
        FifthActivityContract()
    ) { query ->
        Snackbar.make(binding.root, query, Snackbar.LENGTH_SHORT).show();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        binding.buttonTo1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonTo5.setOnClickListener {
            launcher.launch(Unit)
        }
    }
}