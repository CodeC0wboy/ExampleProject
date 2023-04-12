package com.dnc.buendeal.features.mainscreen.ui.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.extentions.dp

class ItemOffsetDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position: Int = parent.getChildAdapterPosition(view)
        when (parent.adapter?.getItemViewType(position)) {
            R.layout.item_title_section -> {
                outRect.set(16.dp, 0, 8.dp, 16.dp)
            }
            R.layout.item_search -> {
                outRect.set(8.dp, 16.dp, 8.dp, 0)
            }
            R.layout.item_banner_view_pager -> {
                outRect.set(8.dp, 8.dp, 8.dp, 0)
            }
            R.layout.item_filters_sort_prod_list -> {
                outRect.set(8.dp, 0, 8.dp, 0)
            }
        }
    }
}
