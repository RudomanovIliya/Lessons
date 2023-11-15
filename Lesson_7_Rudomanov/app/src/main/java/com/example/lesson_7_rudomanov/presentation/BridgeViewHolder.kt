package com.example.lesson_7_rudomanov.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_7_rudomanov.R
import com.example.lesson_7_rudomanov.data.model.Bridge
import com.example.lesson_7_rudomanov.databinding.ItemBridgeBinding
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
        var check: Int = 0
        if (bridge.divorces != null) {
            bridge.divorces.forEach { position -> textViewTime.append(position.start + " - " + position.end + "    ") }
            for (position in bridge.divorces) {
                if (position.start != null && position.end != null && timeDate != null && timeDatePlusHour != null) {
                    val bridgeStartTime: Date? = timeFormat.parse(position.start)
                    val bridgeEndTime: Date? = timeFormat.parse(position.end)
                    if (timeDate.before(bridgeStartTime) || (timeDate.after(bridgeEndTime))) {
                        if (timeDatePlusHour.after(bridgeStartTime) && (timeDate.before(
                                bridgeEndTime
                            ))
                        ) {
                            check = 0
                            break
                        } else {
                            check = 1
                        }
                    } else {
                        check = 2
                        break
                    }

                } else {
                    textViewTitle.text = "Данные не загрузились"
                }
            }

        } else {
            textViewTitle.text = "Данные не загрузились"
        }
        if (check == 0) {
            binding.imageViewBridge.setImageResource(R.drawable.ic_brige_soon)
        } else {
            if ((check == 1)) {
                binding.imageViewBridge.setImageResource(R.drawable.ic_brige_normal)
            } else {
                binding.imageViewBridge.setImageResource(R.drawable.ic_brige_late)
            }
        }
        root.setOnClickListener {
            bridgeListener.onBridgeClick(bridge, check)
        }

    }
}