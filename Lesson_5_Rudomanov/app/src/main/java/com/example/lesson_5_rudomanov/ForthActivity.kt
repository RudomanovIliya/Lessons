package com.example.lesson_5_rudomanov

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_5_rudomanov.databinding.ActivityForthBinding
import java.text.SimpleDateFormat

class ForthActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityForthBinding::bind)
    private val timeMillis = System.currentTimeMillis()
    private val time by lazy { intent.getStringExtra(KEY_TIME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forth)

        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS")
        val currentTime = time?.toLong()
        val currentDate: String = dateFormat.format(currentTime)
        binding.textView4Time.text = currentDate

        binding.buttonTo4.setOnClickListener {
            val intent = (ForthActivity.createStartIntent(this, timeMillis.toString()))
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS")
        val currentTime = time?.toLong()
        val currentDate: String = dateFormat.format(currentTime)
        binding.textView4Time.text = currentDate

    }

    companion object {
        private const val KEY_TIME = "key_time"

        fun createStartIntent(context: Context, value: String): Intent {
            return Intent(context, ForthActivity::class.java).apply {
                putExtra(KEY_TIME, value)
            }
        }
    }
}