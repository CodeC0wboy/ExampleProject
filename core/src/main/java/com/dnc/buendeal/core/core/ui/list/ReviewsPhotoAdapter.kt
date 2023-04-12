package com.dnc.buendeal.core.core.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.base.recycler.DefaultDiffItemCallback
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.databinding.ItemSingleProductReviewPhotoBinding
import com.dnc.buendeal.core.extentions.loadImage

class ReviewsPhotoAdapter :
    ListAdapter<Image, ReviewsPhotoAdapter.ReviewsPhotoViewHolder>(DefaultDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsPhotoViewHolder =
        ReviewsPhotoViewHolder(
            ItemSingleProductReviewPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: ReviewsPhotoViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    class ReviewsPhotoViewHolder(private val binding: ItemSingleProductReviewPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Image) {
            binding.prodOneReviewPhoto.loadImage(item)
        }
    }
}
