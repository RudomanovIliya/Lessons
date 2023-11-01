package com.example.lesson_4_rudomanov

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_4_rudomanov.databinding.BaseInfoItemCardBinding

class BaseViewHolder(
    parent: ViewGroup,
    private val onItemClick: (BaseInfoItem) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.base_info_item_card, parent, false),
) {
    private val binding by viewBinding(BaseInfoItemCardBinding::bind)

    fun bind(product: BaseInfoItem) = with(binding) {
        root.setOnClickListener {
            onItemClick(product)
        }
        imageViewCard.setImageDrawable(product.img)
        textViewTitle.text = product.title
    }
}