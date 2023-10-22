package com.example.homework0

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var buttonActivityOne: Button? = null
    private var buttonActivityTwo: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonActivityOne = findViewById<Button>(R.id.buttonActivityOne)
        findViewById<Button>(R.id.buttonActivityOne).setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }
        buttonActivityTwo = findViewById<Button>(R.id.buttonActivityTwo)
        findViewById<Button>(R.id.buttonActivityTwo).setOnClickListener {
            val intent = Intent(this@MainActivity, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}