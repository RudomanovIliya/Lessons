package com.example.lesson_6_rudomanov


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val firstFragment: FirstFragment by lazy { FirstFragment.newInstance() }
    private val secondFragment: SecondFragment by lazy { SecondFragment.newInstance() }
    private val thirdFragment: ThirdFragment by lazy { ThirdFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, firstFragment)
            .commit()

        binding.bottomNavigationView.menu.findItem(R.id.fragmentFirst).setOnMenuItemClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, firstFragment)
                .commit()
            true
        }
        binding.bottomNavigationView.menu.findItem(R.id.fragmentSecond).setOnMenuItemClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, secondFragment)
                .commit()
            true
        }
        binding.bottomNavigationView.menu.findItem(R.id.fragmentThird).setOnMenuItemClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, thirdFragment)
                .commit()
            true
        }
    }
    /*
    override fun onStart() {
        super.onStart()
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setupWithNavController(
            Navigation.findNavController(this, R.id.fragmentContainerView)
        )
    }
     */
}