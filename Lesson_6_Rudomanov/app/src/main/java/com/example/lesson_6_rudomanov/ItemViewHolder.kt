package com.example.lesson_6_rudomanov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.RecyclerItemBinding

class ItemViewHolder(
    parent: ViewGroup,

    ) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
) {
    private val binding by viewBinding(RecyclerItemBinding::bind)

    fun bind(item: InfoItem) = with(binding) {

        if (item.isElectro) {
            textViewNewInfo.text = "День"
            textViewNewInfoTwo.text = "Ночь"
            textViewNewInfoThree.text = "Пик"
            imageViewAlert.visibility = View.GONE
            textViewAlert.setTextColor(R.color.black)
        } else {
            editTextNewInfo.width = ViewGroup.LayoutParams.MATCH_PARENT;
            textViewNewInfo.text = "Новые показания"
            textViewNewInfoTwo.visibility = View.GONE
            textViewNewInfoThree.visibility = View.GONE
            editTextNewInfoTwo.visibility = View.GONE
            editTextNewInfoThree.visibility = View.GONE
        }
        imageViewItem.setImageResource(item.imageRes)
        textViewTitle.text = item.title
        textViewSerialNumber.text = item.serialNumber
        textViewAlert.text = item.alert
    }
}