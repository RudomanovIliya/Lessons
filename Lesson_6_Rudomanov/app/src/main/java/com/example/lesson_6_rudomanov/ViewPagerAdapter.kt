package com.example.lesson_6_rudomanov

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class ViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    private val items = mutableListOf<CatItem>()

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int,): Fragment=ViewPagerFragment().apply {
        arguments = bundleOf(
            "image" to items[position].imageRes,
            "position" to items[position].stringNumber,
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<CatItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}