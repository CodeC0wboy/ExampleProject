package com.dnc.buendeal.features.search.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.databinding.ItemTitleCategorySearchScreenBinding
import com.dnc.buendeal.features.search.ui.model.TitleSearchItemModel

class TitleSearchAdapterDelegate(context: Context) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): TitleSearchViewHolder =
        TitleSearchViewHolder(
            ItemTitleCategorySearchScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        (viewHolder as TitleSearchViewHolder)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is TitleSearchItemModel

    override fun getViewType(): Int = R.layout.item_title_category_search_screen

    class TitleSearchViewHolder(binding: ItemTitleCategorySearchScreenBinding) :
        RecyclerView.ViewHolder(binding.root)
}
