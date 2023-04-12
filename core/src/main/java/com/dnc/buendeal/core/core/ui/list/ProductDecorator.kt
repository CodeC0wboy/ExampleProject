package com.dnc.buendeal.core.core.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.extentions.dp

class ProductDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.layoutManager) {
            is GridLayoutManager -> {
                val layoutManager = parent.layoutManager as GridLayoutManager
                outRect.right =
                    if (parent.getChildAdapterPosition(view) % layoutManager.spanCount == 0) 8.dp else 16.dp
                outRect.left =
                    if (parent.getChildAdapterPosition(view) % layoutManager.spanCount == 0) 16.dp else 8.dp
                outRect.bottom = 24.dp
            }
            is LinearLayoutManager -> {
                parent.adapter?.let { adapter ->
                    outRect.right = when (parent.getChildAdapterPosition(view)) {
                        adapter.itemCount - 1 -> 16.dp
                        else -> 8.dp
                    }
                    outRect.left = when (parent.getChildAdapterPosition(view)) {
                        0 -> 16.dp
                        else -> 8.dp
                    }
                    outRect.bottom = 36.dp
                }
            }
        }
    }
}
