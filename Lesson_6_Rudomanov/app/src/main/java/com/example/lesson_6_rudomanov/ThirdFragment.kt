package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class ThirdFragment : Fragment(R.layout.fragment_third) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonShowFragment).text = "ПОКАЗАТЬ БАННЕР"
        val viewPagerFragment = FourthFragment()
        var checkButton: Boolean = true
        view.findViewById<Button>(R.id.buttonShowFragment).setOnClickListener {
            if (checkButton) {
                val transaction = childFragmentManager.beginTransaction()

                transaction.add(R.id.fragmentContainerView, viewPagerFragment)

                transaction.commit()
                view.findViewById<Button>(R.id.buttonShowFragment).text = "СКРЫТЬ БАННЕР"
                checkButton = false
            } else {
                val transaction = childFragmentManager.beginTransaction()

                transaction.remove(viewPagerFragment)

                transaction.commit()
                view.findViewById<Button>(R.id.buttonShowFragment).text = "ПОКАЗАТЬ БАННЕР"
                checkButton = true
            }
        }
    }
}