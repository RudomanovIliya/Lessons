package com.example.lesson_6_rudomanov


import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView


class ThirdFragment : Fragment(R.layout.fragment_third) {
    private var checkButton: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonShowFragment).text = getString(R.string.show_banner)
        var fourthFragment: Fragment = FourthFragment.newInstance()
        view.findViewById<Button>(R.id.buttonShowFragment).setOnClickListener {
            if (checkButton) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, fourthFragment)
                    .commit()
                view.findViewById<Button>(R.id.buttonShowFragment).text =
                    getString(R.string.hide_banner)
            } else {
                childFragmentManager.beginTransaction()
                    .remove(fourthFragment)
                    .commit()
                view.findViewById<Button>(R.id.buttonShowFragment).text =
                    getString(R.string.show_banner)
                fourthFragment = FourthFragment.newInstance()
            }
            checkButton = !checkButton
        }
    }

    companion object {
        fun newInstance(): ThirdFragment {
            return ThirdFragment()
        }
    }
}