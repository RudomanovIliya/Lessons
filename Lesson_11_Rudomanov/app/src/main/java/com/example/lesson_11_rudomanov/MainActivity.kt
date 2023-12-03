package com.example.lesson_11_rudomanov

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_11_rudomanov.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val timeNow: Calendar = Calendar.getInstance()
        val listItem = mutableListOf<Item>()

        lifecycleScope.launch {
            repeat(9) {
                timeNow.add(Calendar.DAY_OF_YEAR, -1)
                listItem.add(Item(timeNow.time, Random.nextInt(100)))
                println(timeNow.time)
            }
            listItem.reverse()
            delay(2000)
            binding.visitingView.setData(listItem)
        }

    }
}