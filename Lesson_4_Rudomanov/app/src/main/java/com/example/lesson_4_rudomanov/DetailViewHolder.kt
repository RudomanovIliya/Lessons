package com.example.lesson_4_rudomanov

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_4_rudomanov.databinding.DetailInfoItemCardBinding


class DetailViewHolder(
    parent: ViewGroup,
    private val detailListener: DetailListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.detail_info_item_card, parent, false)
) {
    private val binding by viewBinding(DetailInfoItemCardBinding::bind)

    fun bind(detail: DetailInfoItem) = with(binding) {
        root.setOnClickListener {
            detailListener.onDetailClick(detail)
        }
        imageViewCard.setImageResource(detail.imageRes)
        textViewTitle.text = detail.title
        textViewPrompt.setTextColor(detail.colorRes)
        textViewPrompt.text = detail.prompt
    }
}