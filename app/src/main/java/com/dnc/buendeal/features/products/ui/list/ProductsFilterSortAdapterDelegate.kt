package com.dnc.buendeal.features.products.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.SortCriteria
import com.dnc.buendeal.core.core.ui.model.FilterSortItemModel
import com.dnc.buendeal.databinding.ItemFiltersSortProdListBinding

class ProductsFilterSortAdapterDelegate(
    context: Context,
    private val filterListener: (ItemModel) -> Unit,
    private val sortListener: ((SortCriteria?) -> Unit)? = null
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemFiltersSortProdListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as FilterSortItemModel
        (viewHolder as SearchViewHolder).bind(currentItem, filterListener, sortListener)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is FilterSortItemModel

    override fun getViewType(): Int = R.layout.item_filters_sort_prod_list

    class SearchViewHolder(private val binding: ItemFiltersSortProdListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            filterSort: FilterSortItemModel,
            filterListener: (ItemModel) -> Unit,
            sortListener: ((SortCriteria?) -> Unit)? = null
        ) {
            val adapter = ArrayAdapter(
                binding.root.context,
                R.layout.dropdown_item,
                filterSort.criteriaList.map { binding.root.context.getString(it.value) }
            )

            binding.btnFilters.setOnClickListener { filterListener.invoke(filterSort) }
            binding.dropdownMenuSort.setupAdapter(adapter)
            binding.dropdownMenuSort.setonItemClickListener { parent, view, position, id ->
                sortListener?.invoke(
                    filterSort.criteriaList.getOrNull(position)
                )
            }
        }
    }
}
