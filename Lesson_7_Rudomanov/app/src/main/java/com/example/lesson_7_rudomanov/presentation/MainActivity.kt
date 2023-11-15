package com.example.lesson_7_rudomanov.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val listBridgeFragment: ListBridgeFragment by lazy { ListBridgeFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentConteinerView,listBridgeFragment)
            .commit()
    }
}