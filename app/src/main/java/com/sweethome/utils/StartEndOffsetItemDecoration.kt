package com.sweethome.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class StartEndOffsetItemDecoration(private val offsetPx: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return
        }

        if (itemPosition == 0) {
            outRect.left = offsetPx
            outRect.right = offsetPx / 2
            outRect.top = offsetPx / 2
            outRect.bottom = offsetPx / 2
            return
        }

        val adapter = parent.adapter
        if (adapter != null && adapter.itemCount - 1 == itemPosition) {
            outRect.right = offsetPx
            outRect.left = offsetPx / 2
            outRect.top = offsetPx / 2
            outRect.bottom = offsetPx / 2
            return
        }

        outRect.right = offsetPx / 2
        outRect.left = offsetPx / 2
        outRect.top = offsetPx / 2
        outRect.bottom = offsetPx / 2
    }
}