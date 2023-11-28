package com.example.lesson_8_rudomanov.presentation


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_rudomanov.R
import com.example.lesson_8_rudomanov.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavigationController {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val listNotesFragment: ListNotesFragment by lazy { ListNotesFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, listNotesFragment)
            .commit()
    }

    override fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }
}