package com.dnc.buendeal.features.search.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.databinding.ItemRecentlySearchBinding
import com.dnc.buendeal.features.search.ui.model.RecentSearchItemModel

class RecentSearchAdapterDelegate(context: Context, private val listener: (ItemModel) -> Unit) :
    AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecentSearchViewHolder =
        RecentSearchViewHolder(
            ItemRecentlySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as RecentSearchItemModel
        (viewHolder as RecentSearchViewHolder).bind(currentItem)
        viewHolder.itemView.setOnClickListener { listener.invoke(currentItem) }
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is RecentSearchItemModel

    override fun getViewType(): Int = R.layout.item_recently_search

    class RecentSearchViewHolder(private val binding: ItemRecentlySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recentSearch: RecentSearchItemModel) {
            binding.recentTitle.text = recentSearch.title
        }
    }
}
