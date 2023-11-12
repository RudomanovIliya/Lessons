package com.example.lesson_6_rudomanov


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding.toolbar.menu.findItem(R.id.item_info).setOnMenuItemClickListener {
            Toast.makeText(this, "Информация", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_change).setOnMenuItemClickListener {
            Toast.makeText(this, "Изменить", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_settings).setOnMenuItemClickListener {
            Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_another).setOnMenuItemClickListener {
            Toast.makeText(this, "Другое", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun onStart() {
        super.onStart()
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setupWithNavController(
            Navigation.findNavController(this, R.id.fragmentContainerView)
        )
    }
}