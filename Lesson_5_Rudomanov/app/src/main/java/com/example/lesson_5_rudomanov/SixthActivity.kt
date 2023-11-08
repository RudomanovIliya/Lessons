package com.example.lesson_5_rudomanov

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lesson_5_rudomanov.databinding.ActivityMainBinding
import com.example.lesson_5_rudomanov.databinding.ActivitySixthBinding
import com.google.android.material.snackbar.Snackbar

class SixthActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySixthBinding::bind)

    private val itemsAdapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        binding.recyclerViewItem.adapter = itemsAdapter.apply {
            itemListener = ItemListener { item ->
                Toast.makeText(this@SixthActivity, item.name, Toast.LENGTH_SHORT).show()
            }
        }

        val items = listOf(
            InfoItem(
                R.drawable.item_1,
                "Царь пышка",
                "Скидка 10% на выпечку по коду",
                "Лермонтовский пр, 52, МО №1",
            ),
            InfoItem(
                R.drawable.item_2,
                "Химчистка «МАЙ?»",
                "Скидка 5% на чистку пальто",
                "Лермонтовский пр, 48",
            ),
            InfoItem(
                R.drawable.item_3,
                "Шаверма У Ашота ",
                "При покупке шавермы,\n фалафель бесплатно",
                "Лермонтовский пр, 52, МО №1",
            ),
        )


        itemsAdapter.setList(items)
    }
}