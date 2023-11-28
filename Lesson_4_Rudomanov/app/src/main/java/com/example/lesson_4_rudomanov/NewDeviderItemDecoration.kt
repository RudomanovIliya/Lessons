package com.example.lesson_4_rudomanov

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class NewDeviderItemDecoration(private val divider: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {
            top = divider
            bottom = divider
            left = divider
            right = divider

        }
    }
}