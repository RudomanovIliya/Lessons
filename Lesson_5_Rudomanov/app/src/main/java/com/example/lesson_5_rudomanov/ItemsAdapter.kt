package com.example.lesson_5_rudomanov

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ItemsAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    private val items = mutableListOf<InfoItem>()


    lateinit var itemListener: ItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ItemViewHolder {
        return  ItemViewHolder(parent, itemListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder:ItemViewHolder, position: Int) {
        holder.bind((items[position]))

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<InfoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}