package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.SearchItemModel
import com.dnc.buendeal.core.databinding.ItemSearchBinding

class SearchAdapterDelegate(
    context: Context,
    private val searchCallback: (SearchItemModel) -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as SearchItemModel
        (viewHolder as SearchViewHolder).bind(currentItem, searchCallback)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean = item is SearchItemModel

    override fun getViewType(): Int = R.layout.item_search

    class SearchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(search: SearchItemModel, searchCallback: (SearchItemModel) -> Unit) {
            binding.categoriesSearch.onSearchClick = { searchCallback.invoke(search) }
        }
    }
}
