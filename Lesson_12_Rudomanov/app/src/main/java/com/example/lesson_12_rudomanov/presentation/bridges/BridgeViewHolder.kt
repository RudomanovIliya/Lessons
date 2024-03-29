package com.example.lesson_12_rudomanov.presentation.bridges

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_12_rudomanov.R
import com.example.lesson_12_rudomanov.databinding.ItemBridgeBinding
import com.example.lesson_12_rudomanov.presentation.bridges.model.Bridge

class BridgeViewHolder(
    parent: ViewGroup,
    private val bridgeListener: BridgeListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_bridge, parent, false)
) {
    private val binding by viewBinding(ItemBridgeBinding::bind)
    fun bind(bridge: Bridge) {
        binding.root.setOnClickListener {
            bridgeListener.onBridgeClick(bridge)
        }
        binding.root.bind(bridge)
    }
}