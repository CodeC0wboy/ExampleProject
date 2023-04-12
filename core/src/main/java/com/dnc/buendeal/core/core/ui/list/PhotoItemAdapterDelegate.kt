package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.PhotoItemModel
import com.dnc.buendeal.core.databinding.ItemWishlistPhotoProductBinding
import com.dnc.buendeal.core.extentions.loadImage

class PhotoItemAdapterDelegate(context: Context) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemWishlistPhotoProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as PhotoItemModel
        (viewHolder as PhotoItemViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean = item is PhotoItemModel

    override fun getViewType(): Int = R.layout.item_wishlist_photo_product

    class PhotoItemViewHolder(private val binding: ItemWishlistPhotoProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PhotoItemModel) {
            binding.itemWishlistPhoto.loadImage(item.url)
        }
    }
}
