package com.dnc.buendeal.features.mainscreen.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.HelpItemModel
import com.dnc.buendeal.core.databinding.ItemHelpBinding

class HelpDelegate(
    context: Context,
    private val onHideClick: (HelpItemModel) -> Unit,
    private val onLearnMoreClick: () -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemHelpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return HelpViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as HelpItemModel
        (viewHolder as HelpViewHolder).bind(onHideClick, onLearnMoreClick, currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is HelpItemModel

    override fun getViewType(): Int = R.layout.item_help

    class HelpViewHolder(private val binding: ItemHelpBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onHideClick: (HelpItemModel) -> Unit, onLearnMoreClick: () -> Unit, item: HelpItemModel) {
            binding.apply {
                hideBtn.setOnClickListener {
                    onHideClick.invoke(item)
                }
                learnMoreBtn.setOnClickListener {
                    onLearnMoreClick.invoke()
                }
            }
        }
    }
}
