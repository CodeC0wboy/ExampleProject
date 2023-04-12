package com.dnc.buendeal.core.core.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.extentions.dp

class PhotoReviewDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        parent.adapter?.let { adapter ->
            outRect.right = when (parent.getChildAdapterPosition(view)) {
                adapter.itemCount - 1 -> 0
                else -> 4.dp
            }
            outRect.left = when (parent.getChildAdapterPosition(view)) {
                0 -> 0
                else -> 4.dp
            }
            outRect.top = 8.dp
        }
    }
}
