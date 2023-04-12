package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.FilterSortItemModel
import com.dnc.buendeal.core.databinding.ItemFilterSortMyReviewsBinding

class MyReviewsQuestionsSortAdapterDelegate(
    context: Context
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemFilterSortMyReviewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as FilterSortItemModel
        (viewHolder as SearchViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is FilterSortItemModel

    override fun getViewType(): Int = R.layout.item_filter_sort_my_reviews

    class SearchViewHolder(private val binding: ItemFilterSortMyReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            filterSort: FilterSortItemModel
        ) {
            val adapter = ArrayAdapter(
                binding.root.context,
                R.layout.dropdown_item,
                filterSort.criteriaList.map { binding.root.context.getString(it.value) }
            )
            binding.dropdownMenuSort.setupAdapter(adapter)
        }
    }
}
