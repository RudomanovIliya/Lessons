package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.internal.findRootView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private val binding by viewBinding(FragmentSecondBinding::bind)
    private val itemsAdapter = ItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewCard.adapter = itemsAdapter
        val items = listOf(
            InfoItem(
                R.drawable.ic_water_cold,
                "Холодная вода",
                "54656553",
                false,
                "Необходимо подать показания до 25.03.18",
            ),
            InfoItem(
                R.drawable.ic_water_hot,
                "Горячая вода",
                "54656553",
                false,
                "Необходимо подать показания до 25.03.18",
            ),
            InfoItem(
                R.drawable.ic_electro,
                "Электроэнергия",
                "54656553",
                true,
                "Показания сданы 16.02.18 и будут учтены при\n следующем расчете 25.02.18",
            ),
        )
        itemsAdapter.setList(items)
    }
}