package com.dnc.buendeal.features.products.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.extentions.dp

class InnerItemDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val layoutManager = parent.layoutManager as GridLayoutManager
        outRect.right =
            if (parent.getChildAdapterPosition(view) % layoutManager.spanCount == 0) 8.dp else 16.dp
        outRect.left =
            if (parent.getChildAdapterPosition(view) % layoutManager.spanCount == 0) 16.dp else 8.dp
        outRect.bottom = 24.dp
    }
}
