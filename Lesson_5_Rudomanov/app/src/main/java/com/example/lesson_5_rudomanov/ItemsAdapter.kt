package com.example.lesson_5_rudomanov

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

const val TYPE_ITEM = 0


class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<InfoItem>()


    lateinit var itemListener: ItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> ItemViewHolder(parent, itemListener)
            else -> error("ViewType not supported")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind((items[position]))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_ITEM
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<InfoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}