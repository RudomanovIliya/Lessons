package com.example.lesson_8_rudomanov.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AlertIconAdapter : RecyclerView.Adapter<AlertViewHolder>() {
    private val icons = mutableListOf<IconAlert>()
    private var checkColor: Int? = null
    lateinit var alertListener: AlertListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        return AlertViewHolder(parent, alertListener, checkColor)
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(icons[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(notes: MutableList<IconAlert>, checkColor: Int?) {
        this.checkColor = checkColor
        this.icons.clear()
        this.icons.addAll(notes)
        notifyDataSetChanged()
    }
}