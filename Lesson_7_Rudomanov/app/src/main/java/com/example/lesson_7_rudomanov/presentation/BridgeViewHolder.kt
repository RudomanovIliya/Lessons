package com.example.lesson_7_rudomanov.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.data.model.Bridge
import com.example.lesson_7_rudomanov.databinding.ItemBridgeBinding
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val ONE_HOUR = 60 * 60 * 1000

class BridgeViewHolder(
    parent: ViewGroup,
    private val bridgeListener: BridgeListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_bridge, parent, false)
) {
    private val binding by viewBinding(ItemBridgeBinding::bind)

    fun bind(bridge: Bridge) = with(binding) {
        val timeFormat: DateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val currentDate = Date()
        val timeText: String = timeFormat.format(currentDate)
        val timeDate: Date? = timeFormat.parse(timeText)

        val currentDatePlusHour = System.currentTimeMillis() + ONE_HOUR
        val timeTextPlusHour: String = timeFormat.format(currentDatePlusHour)
        val timeDatePlusHour: Date? = timeFormat.parse(timeTextPlusHour)

        textViewTitle.text = bridge.name
        var stateBridge: Int = 0
        if (bridge.divorces != null) {
            bridge.divorces.forEach { position ->
                val stringBuilderTime = StringBuilder()
                stringBuilderTime.append(position.start).append(" - ").append(position.end)
                    .append("    ")
                textViewTime.append(stringBuilderTime)
            }
            for (position in bridge.divorces) {
                if (position.start != null && position.end != null && timeDate != null && timeDatePlusHour != null) {
                    val bridgeStartTime: Date? = timeFormat.parse(position.start)
                    val bridgeEndTime: Date? = timeFormat.parse(position.end)
                    if (timeDate.before(bridgeStartTime) || (timeDate.after(bridgeEndTime))) {
                        if (timeDatePlusHour.after(bridgeStartTime) && (timeDate.before(
                                bridgeEndTime
                            ))
                        ) {
                            stateBridge = 0
                            break
                        } else {
                            stateBridge = 1
                        }
                    } else {
                        stateBridge = 2
                        break
                    }
                }
            }
        }
        when (stateBridge) {
            0 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_soon)
            1 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_normal)
            2 -> binding.imageViewBridge.setImageResource(R.drawable.ic_brige_late)
        }
        root.setOnClickListener {
            bridgeListener.onBridgeClick(bridge, stateBridge)
        }

    }
}