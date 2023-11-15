package com.example.lesson_7_rudomanov.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_7_rudomanov.data.model.Bridge


class BridgesAdapter : RecyclerView.Adapter<BridgeViewHolder>() {

    private val bridges = mutableListOf<Bridge>()
    lateinit var bridgeListener: BridgeListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BridgeViewHolder {
        return BridgeViewHolder(parent, bridgeListener)
    }

    override fun getItemCount(): Int {
        return bridges.size
    }

    override fun onBindViewHolder(holder: BridgeViewHolder, position: Int) {
        holder.bind(bridges[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(bridges: List<Bridge>) {
        this.bridges.clear()
        this.bridges.addAll(bridges)
        notifyDataSetChanged()
    }
}