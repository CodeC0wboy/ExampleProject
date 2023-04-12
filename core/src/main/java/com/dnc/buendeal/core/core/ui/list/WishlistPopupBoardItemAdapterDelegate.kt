package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.ui.model.BoardItemModel
import com.dnc.buendeal.core.databinding.ItemWishlistPopupBoardBinding

class WishlistPopupBoardItemAdapterDelegate(
    context: Context,
    private val itemListener: (BoardItemModel) -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemWishlistPopupBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WishlistPopupBoardItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as BoardItemModel
        (viewHolder as WishlistPopupBoardItemViewHolder).bind(currentItem, itemListener)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean = item is BoardItemModel

    override fun getViewType(): Int = R.layout.item_wishlist_popup_board

    class WishlistPopupBoardItemViewHolder(private val binding: ItemWishlistPopupBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = RecyclerDelegationAdapter(binding.root.context).apply {
            addDelegate(PhotoItemAdapterDelegate(binding.root.context))
        }

        init {
            binding.pairRv.adapter = adapter
            binding.pairRv.addItemDecoration(ComparisonPairOffsetDecoration())
        }

        fun bind(
            item: BoardItemModel,
            itemListener: (BoardItemModel) -> Unit
        ) {
            binding.apply {
                val count = "${item.totalProducts} products"
                popupBoardItem.setOnClickListener {
                    itemListener.invoke(item)
                }
                titleTv.text = item.name
                productCountTv.text = count

                adapter.setItems(item.previewItems)
            }
        }
    }
}
