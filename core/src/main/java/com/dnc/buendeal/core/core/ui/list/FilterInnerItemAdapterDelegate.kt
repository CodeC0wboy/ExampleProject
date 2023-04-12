package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.filter.FilterInnerOptionsItemModel
import com.dnc.buendeal.core.databinding.ItemPickFilterBinding

class FilterInnerItemAdapterDelegate(
    context: Context,
    private val pickFilterListener: (FilterInnerOptionsItemModel) -> Unit,
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemPickFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterInnerItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as FilterInnerOptionsItemModel
        (viewHolder as FilterInnerItemViewHolder).bind(currentItem, pickFilterListener)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is FilterInnerOptionsItemModel

    override fun getViewType(): Int = R.layout.item_pick_filter

    class FilterInnerItemViewHolder(
        private val binding: ItemPickFilterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: FilterInnerOptionsItemModel,
            pickFilterListener: (FilterInnerOptionsItemModel) -> Unit,
        ) {
            binding.innerPickFilter.text = item.label

            item.isChecked?.also {
                binding.innerPickFilter.isChecked = it
            }

            binding.innerPickFilter.setOnCheckedChangeListener { _, isChecked ->
                item.isChecked = isChecked
                pickFilterListener.invoke(item)
            }
        }
    }
}
