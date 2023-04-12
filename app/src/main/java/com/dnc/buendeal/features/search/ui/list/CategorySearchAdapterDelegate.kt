package com.dnc.buendeal.features.search.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.databinding.ItemCategorySearchScreenBinding
import com.dnc.buendeal.features.search.ui.model.CategorySearchItemModel

class CategorySearchAdapterDelegate(context: Context, private val listener: (ItemModel) -> Unit) :
    AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): CategorySearchViewHolder =
        CategorySearchViewHolder(
            ItemCategorySearchScreenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as CategorySearchItemModel
        (viewHolder as CategorySearchViewHolder).bind(currentItem)
        viewHolder.itemView.setOnClickListener { listener.invoke(currentItem) }
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is CategorySearchItemModel

    override fun getViewType(): Int = R.layout.item_category_search_screen

    class CategorySearchViewHolder(private var binding: ItemCategorySearchScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categorySearch: CategorySearchItemModel) {
            binding.categoryTitle.text = categorySearch.title
        }
    }
}
