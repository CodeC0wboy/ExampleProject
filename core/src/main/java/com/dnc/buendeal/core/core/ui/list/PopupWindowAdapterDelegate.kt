package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.base.recycler.ShowDivider
import com.dnc.buendeal.core.core.ui.model.PopupWindowItemModel
import com.dnc.buendeal.core.databinding.ItemPopupWindowBinding
import com.dnc.buendeal.core.extentions.loadImage

class PopupWindowAdapterDelegate(
    context: Context,
    private val listener: (PopupWindowItemModel) -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): PopupViewHolder = PopupViewHolder(
        ItemPopupWindowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        false
    )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as PopupWindowItemModel
        (viewHolder as PopupViewHolder).bind(currentItem)
        viewHolder.itemView.setOnClickListener { listener.invoke(currentItem) }
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is PopupWindowItemModel

    override fun getViewType(): Int = R.layout.item_popup_window

    class PopupViewHolder(
        private val binding: ItemPopupWindowBinding,
        override var shouldShowDivider: Boolean
    ) : RecyclerView.ViewHolder(binding.root), ShowDivider {

        fun bind(item: PopupWindowItemModel) {
            binding.apply {
                imageIv.loadImage(item.type.imageRes)
                titleTv.text = binding.root.context.getString(item.type.title)
            }
        }
    }
}
