package com.dnc.buendeal.features.search.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.extentions.dp

class SearchScreenItemOffsetDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position: Int = parent.getChildAdapterPosition(view)
        when (parent.adapter?.getItemViewType(position)) {
            R.layout.item_recently_search -> {
                outRect.set(8.dp, 0, 8.dp, 0)
            }
            R.layout.item_title_category_search_screen -> {
                outRect.set(16.dp, 8.dp, 16.dp, 8.dp)
            }
            R.layout.item_category_search_screen -> {
                outRect.set(8.dp, 0, 8.dp, 0)
            }
        }
    }
}
