package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.WriteReviewPhotoItemModel
import com.dnc.buendeal.core.databinding.ItemWriteReviewPhotoBinding
import com.dnc.buendeal.core.extentions.loadImage

class WriteReviewPhotoAdapterDelegate(
    context: Context
) : AdapterDelegate(context) {

    override fun onCreateViewHolder(parent: ViewGroup): WriteReviewPhotoViewHolder =
        WriteReviewPhotoViewHolder(
            ItemWriteReviewPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as WriteReviewPhotoItemModel
        (viewHolder as WriteReviewPhotoViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is WriteReviewPhotoItemModel

    override fun getViewType(): Int = R.layout.item_write_review_photo

    class WriteReviewPhotoViewHolder(
        private val binding: ItemWriteReviewPhotoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WriteReviewPhotoItemModel) {
            binding.itemWriteReviewPhoto.loadImage(item.uri)
        }
    }
}
