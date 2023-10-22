package com.example.homework0


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.TreeSet

class SecondActivity : AppCompatActivity() {
    var names = TreeSet<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            names.add(findViewById<EditText>(R.id.editTextName).text.toString())
        }
        findViewById<Button>(R.id.buttonShow).setOnClickListener {
            findViewById<TextView>(R.id.textViewName).text = ""
            for (name in names) {
                findViewById<TextView>(R.id.textViewName).append(name + "\n")
            }
        }
    }
}