package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.FragmentFirstBinding
import com.example.lesson_6_rudomanov.databinding.FragmentSecondBinding

class FirstFragment : Fragment(R.layout.fragment_first) {

    private val binding by viewBinding(FragmentFirstBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.menu.findItem(R.id.item_info).setOnMenuItemClickListener {
            Toast.makeText(activity, "Информация", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_change).setOnMenuItemClickListener {
            Toast.makeText(activity, "Изменить", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_settings).setOnMenuItemClickListener {
            Toast.makeText(activity, "Настройки", Toast.LENGTH_SHORT).show()
            true
        }
        binding.toolbar.menu.findItem(R.id.item_another).setOnMenuItemClickListener {
            Toast.makeText(activity, "Другое", Toast.LENGTH_SHORT).show()
            true
        }
    }

    companion object {
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }
}