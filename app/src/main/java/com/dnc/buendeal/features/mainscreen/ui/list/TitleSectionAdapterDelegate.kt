package com.dnc.buendeal.features.mainscreen.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.TitleSectionItemModel
import com.dnc.buendeal.databinding.ItemTitleSectionBinding

class TitleSectionAdapterDelegate(
    context: Context,
    private val titleSectionListener: (ItemModel) -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemTitleSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TitleSectionViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as TitleSectionItemModel
        (viewHolder as TitleSectionViewHolder).bind(currentItem, titleSectionListener)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is TitleSectionItemModel

    override fun getViewType(): Int = R.layout.item_title_section

    class TitleSectionViewHolder(private val binding: ItemTitleSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(section: TitleSectionItemModel, titleSectionListener: (ItemModel) -> Unit) {
            binding.apply {
                titleSection.text = section.title
                textSeeAll.isVisible = section.visibility
                textSeeAll.setOnClickListener { titleSectionListener.invoke(section) }
            }
        }
    }
}
