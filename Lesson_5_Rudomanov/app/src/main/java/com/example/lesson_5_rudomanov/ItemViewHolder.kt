package com.example.lesson_5_rudomanov

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.lesson_5_rudomanov.databinding.ItemInfoBinding

class ItemViewHolder(
    parent: ViewGroup,
    private val itemListener: ItemListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false)
) {
    private val binding by viewBinding(ItemInfoBinding::bind)

    fun bind(item: InfoItem) = with(binding) {
        root.setOnClickListener {
           itemListener.onItemClick(item)
        }
        Glide
            .with(binding.root)
            .load(item.image)
            .into(binding.imageViewItem);
        textViewName.text = item.name
        textViewDiscount.text = item.discount
        textViewAddress.text = item.address
    }
}