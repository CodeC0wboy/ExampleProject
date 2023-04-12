package com.dnc.buendeal.features.search.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.databinding.ItemFiltersSortSearchResultsBinding
import com.dnc.buendeal.features.search.ui.model.CategoryFilterItemModel
import com.dnc.buendeal.features.search.ui.model.SearchResultsSortFilterItemModel

class SearchResultsFilterSortAdapterDelegate(
    context: Context,
    private val filterListener: (ItemModel) -> Unit,
    private val categoriesFilterListener: (CategoryFilterItemModel) -> Unit
) : AdapterDelegate(context) {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemFiltersSortSearchResultsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as SearchResultsSortFilterItemModel
        (viewHolder as SearchViewHolder).bind(currentItem, filterListener, categoriesFilterListener)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is SearchResultsSortFilterItemModel

    override fun getViewType(): Int = R.layout.item_filters_sort_search_results

    class SearchViewHolder(private val binding: ItemFiltersSortSearchResultsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            filterSort: SearchResultsSortFilterItemModel,
            filterListener: (ItemModel) -> Unit,
            categoriesFilterListener: (CategoryFilterItemModel) -> Unit
        ) {
            val sortAdapter = ArrayAdapter(
                binding.root.context,
                R.layout.dropdown_item,
                filterSort.sortCriteriaList.map { binding.root.context.getString(it.value) }
            )

            val categoryFilterAdapter = ArrayAdapter(
                binding.root.context,
                R.layout.dropdown_item,
                filterSort.filterCriteriaList.map { it.title }
            )

            binding.btnFilters.setOnClickListener { filterListener.invoke(filterSort) }
            binding.dropdownMenuSort.setupAdapter(sortAdapter)
            binding.dropdownMenuCategoryFilter.setupAdapter(categoryFilterAdapter)
            binding.dropdownMenuCategoryFilter.setonItemClickListener { p0, p1, position, p3 ->
                categoriesFilterListener.invoke(filterSort.filterCriteriaList[position])
            }
        }
    }
}
