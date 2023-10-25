package com.example.homework0

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ThirdActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        var student = HashMap<Long, Student>()
        val editText = findViewById<EditText>(R.id.editTextInfo)
        editText.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                val listInfo =
                    (editText.text).split(" ")
                var idTime = System.currentTimeMillis()
                student[idTime] =
                    Student(idTime, listInfo[0], listInfo[1], listInfo[2], listInfo[3].toInt())
                return@setOnKeyListener true
            }
            false
        }
        findViewById<Button>(R.id.buttonShow).setOnClickListener {
            findViewById<TextView>(R.id.textViewName).text = ""
            student.forEach { (key, value) -> findViewById<TextView>(R.id.textViewName).append(value.returnInfo() + "\n") }
        }
    }
}
