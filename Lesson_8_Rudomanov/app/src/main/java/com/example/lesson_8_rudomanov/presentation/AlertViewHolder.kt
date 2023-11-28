package com.example.lesson_8_rudomanov.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_rudomanov.R
import com.example.lesson_8_rudomanov.databinding.AlertIconBinding

class AlertViewHolder(
    parent: ViewGroup,
    private val alertListener: AlertListener,
    private var checkColor: Int?,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.alert_icon, parent, false)
) {
    private val binding by viewBinding(AlertIconBinding::bind)

    fun bind(icon: IconAlert) = with(binding) {
        root.setOnClickListener {
            alertListener.onAlertClick(icon)
            checkColor = icon.iconRes
        }

        if (checkColor == icon.iconRes) {
            cardViewIcon.foreground = root.context.getDrawable(R.drawable.ic_check)
        }
        cardViewIcon.setCardBackgroundColor(root.context.getColor(icon.iconRes))
    }
}