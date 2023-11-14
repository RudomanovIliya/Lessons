package com.example.lesson_6_rudomanov


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.RecyclerItemBinding

class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
) {
    private val binding by viewBinding(RecyclerItemBinding::bind)

    fun bind(item: InfoItem) = with(binding) {

        if (item.isElectro) {
            textViewNewInfo.text = root.context.getString(R.string.day)
            textViewNewInfoTwo.text = root.context.getString(R.string.night)
            textViewNewInfoThree.text = root.context.getString(R.string.peak)
            imageViewAlert.visibility = View.GONE
            textViewAlert.setTextColor(root.context.getColor(R.color.black))
            textViewAlert.text = item.alertSpan
        } else {
            editTextNewInfoTwo.visibility = View.GONE
            editTextNewInfoThree.visibility = View.GONE
            editTextNewInfo.width = ViewGroup.LayoutParams.MATCH_PARENT;
            textViewNewInfo.text = root.context.getString(R.string.new_data)
            textViewNewInfoTwo.visibility = View.GONE
            textViewNewInfoThree.visibility = View.GONE
            textViewAlert.text = item.alert

        }
        imageViewItem.setImageResource(item.imageRes)
        textViewTitle.text = item.title
        textViewSerialNumber.text = item.serialNumber
    }
}