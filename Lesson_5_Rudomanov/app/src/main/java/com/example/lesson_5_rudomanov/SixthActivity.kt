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
                "http://developer.alexanderklimov.ru/android/images/android_cat.jpg",
                "Царь пышка",
                "Скидка 10% на выпечку по коду",
                "Лермонтовский пр, 52, МО №1",
            ),
            InfoItem(
                "https://img.freepik.com/free-photo/sweet-pastry-assortment-top-view_23-2148516578.jpg?size=626&ext=jpg&ga=GA1.1.34264412.1699315200&semt=sph",
                "Химчистка «МАЙ?»",
                "Скидка 5% на чистку пальто",
                "Лермонтовский пр, 48",
            ),
            InfoItem(
                "https://sedelice.ru/uploads/blog/vypecka-optom.jpg",
                "Шаверма У Ашота ",
                "При покупке шавермы,\n фалафель бесплатно",
                "Лермонтовский пр, 52, МО №1",
            ),
        )


        itemsAdapter.setList(items)
    }
}