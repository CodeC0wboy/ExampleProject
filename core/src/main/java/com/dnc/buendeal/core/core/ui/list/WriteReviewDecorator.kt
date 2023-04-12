package com.dnc.buendeal.core.core.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.extentions.dp

class WriteReviewDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.set(4.dp, 12.dp, 0.dp, 0)
    }
}
