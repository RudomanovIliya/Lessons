package com.example.lesson_6_rudomanov

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ViewPagerFragment : Fragment(R.layout.view_pager_item) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            view.findViewById<ImageView>(R.id.imageViewCat).setImageResource(it.getInt("image"))
            view.findViewById<TextView>(R.id.textViewCatNumber).text = it.getString("position")
        }
        view.findViewById<ImageView>(R.id.imageViewCat).setOnClickListener {
            Toast.makeText(
                activity,
                view.findViewById<TextView>(R.id.textViewCatNumber).text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}