package com.dnc.buendeal.core.core.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.extentions.dp

class WishlistPopupBoardItemOffsetDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(8.dp, 16.dp, 8.dp, 0)
        parent.adapter?.let { adapter ->
            outRect.bottom = when (parent.getChildAdapterPosition(view)) {
                adapter.itemCount - 1 -> 16.dp
                else -> 0.dp
            }
        }
    }
}
