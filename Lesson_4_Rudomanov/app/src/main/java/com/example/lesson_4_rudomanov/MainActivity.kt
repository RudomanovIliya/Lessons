package com.example.lesson_4_rudomanov

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_rudomanov.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    private val itemsAdapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCard)
        val recyclerViewLayoutManager = GridLayoutManager(this, 2)

        val dividerItemDecorationVertical = DividerItemDecoration(this, RecyclerView.VERTICAL)
        dividerItemDecorationVertical.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        recyclerView.addItemDecoration(dividerItemDecorationVertical)

        val dividerItemDecorationHorizontal = DividerItemDecoration(this, RecyclerView.HORIZONTAL)
        dividerItemDecorationHorizontal.setDrawable(resources.getDrawable(R.drawable.divider_drawable))
        recyclerView.addItemDecoration(dividerItemDecorationHorizontal)

        recyclerViewLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (itemsAdapter.getItemViewType(position)) {
                    TYPE_DETAIL -> 1
                    TYPE_BASE -> 2
                    TYPE_DETAIL_BASE -> 2
                    else -> -1
                }
            }
        }
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "Выход", Toast.LENGTH_SHORT).show()
        }
        binding.toolbar.menu.findItem(R.id.item_home).setOnMenuItemClickListener {
            Toast.makeText(this, "Дом", Toast.LENGTH_SHORT).show()
            true
        }

        binding.toolbar.menu.findItem(R.id.item_info).setOnMenuItemClickListener {
            val alertDialogFragment = AlertDialogFragment()
            val manager = supportFragmentManager
            alertDialogFragment.show(manager, "AlertDialog")
            true
        }

        binding.recyclerViewCard.adapter = itemsAdapter.apply {
            onBaseClick = {
                Snackbar.make(binding.root, it.title, Snackbar.LENGTH_SHORT).show();
            }
            detailListener = DetailListener { detail ->
                Snackbar.make(binding.root, detail.title, Snackbar.LENGTH_SHORT).show();
            }
        }
        val items = listOf(
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_ticket),
                    "Квитанции ",
                    "- 40 080,55",
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_meter),
                    "Счетчики ",
                    "Подайте показания "
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_installment),
                    "Рассрочка ",
                    "Сл. платеж 25.02.2018 "
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_insurance),
                    "Страхование ",
                    "Полис до 12.01.2019 "
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_internet),
                    "Интернет и ТВ ",
                    "Баланс 350 Р "
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_interhome),
                    "Домофон ",
                    "Подключен "
                )
            ),
            ListItem.DetailInfoItemSeal(
                DetailInfoItem(
                    getDrawable(R.drawable.ic_security),
                    "Охрана",
                    "Нет"
                )
            ),

            ListItem.BaseInfoItemSeal(
                BaseInfoItem(
                    getDrawable(R.drawable.ic_contacts),
                    "Контакты УК и служб "
                )
            ),
            ListItem.BaseInfoItemSeal(
                BaseInfoItem(
                    getDrawable(R.drawable.ic_request),
                    "Мои заявки "
                )
            ),
            ListItem.BaseInfoItemSeal(
                BaseInfoItem(
                    getDrawable(R.drawable.ic_memo),
                    "Памятка жителя А101 "
                )
            ),
        )
        itemsAdapter.setList(items)
    }
}