package com.example.lesson_6_rudomanov

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_6_rudomanov.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {
    private val binding by viewBinding(FragmentSecondBinding::bind)
    private val itemsAdapter = ItemsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spannableString: String =
            "Показания сданы 16.02.18 и будут учтены при\n следующем расчете 25.02.18"
        val stringAlertSpannable = SpannableString(spannableString)
        stringAlertSpannable.setSpan(StyleSpan(Typeface.BOLD), 16, 24, 0)
        stringAlertSpannable.setSpan(StyleSpan(Typeface.BOLD), 62, stringAlertSpannable.length, 0)
        binding.recyclerViewCard.adapter = itemsAdapter
        val items = listOf(
            InfoItem(
                R.drawable.ic_water_cold,
                "Холодная вода",
                "54656553",
                false,
                "Необходимо подать показания до 25.03.18",
                stringAlertSpannable
            ),
            InfoItem(
                R.drawable.ic_water_hot,
                "Горячая вода",
                "54656553",
                false,
                "Необходимо подать показания до 25.03.18",
                stringAlertSpannable
            ),
            InfoItem(
                R.drawable.ic_electro,
                "Электроэнергия",
                "54656553",
                true,
                getString(R.string.text_alert),
                stringAlertSpannable
            ),
        )
        itemsAdapter.setList(items)
    }

    companion object {
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }
}