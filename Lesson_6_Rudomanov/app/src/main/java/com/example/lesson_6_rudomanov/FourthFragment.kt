package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FourthFragment : Fragment(R.layout.fragment_fourth) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catsAdapter = ViewPagerAdapter(this@FourthFragment)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerCats)
        viewPager.apply {
            viewPager.adapter = catsAdapter
        }

        val items = listOf(
            CatItem(
                R.drawable.cat_one,
                "Картинка 1"
            ),
            CatItem(
                R.drawable.cat_two,
                "Картинка 2"
            ),
            CatItem(
                R.drawable.cat_three,
                "Картинка 3"
            ),
        )
        catsAdapter.setList(items)

        TabLayoutMediator(
            view.findViewById<TabLayout>(R.id.tabLayoutCatsNumber),
            viewPager
        ) { tab, position ->
            tab.icon = when (position) {
                0 -> resources.getDrawable(R.drawable.tab_indicator_selected)
                1 -> resources.getDrawable(R.drawable.tab_indicator_selected)
                2 -> resources.getDrawable(R.drawable.tab_indicator_selected)
                else -> throw IllegalStateException()
            }
        }.attach()
    }
}