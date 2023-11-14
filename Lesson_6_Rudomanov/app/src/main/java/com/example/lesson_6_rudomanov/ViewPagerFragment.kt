package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

private const val KEY_IMAGE = "key_image"
private const val KEY_POSITION = "key_position"

class ViewPagerFragment : Fragment(R.layout.view_pager_item) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            view.findViewById<ImageView>(R.id.imageViewCat).setImageResource(it.getInt(KEY_IMAGE))
            view.findViewById<TextView>(R.id.textViewCatNumber).text = it.getString(KEY_POSITION)
        }
        view.findViewById<ImageView>(R.id.imageViewCat).setOnClickListener {
            Toast.makeText(
                activity,
                view.findViewById<TextView>(R.id.textViewCatNumber).text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        fun newInstance(cat: CatItem): ViewPagerFragment {
            return ViewPagerFragment().apply {
                arguments = bundleOf(
                    KEY_IMAGE to cat.imageRes,
                    KEY_POSITION to cat.stringNumber,
                )
            }

        }
    }
}