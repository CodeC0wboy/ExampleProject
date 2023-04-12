package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.ReviewItemModel
import com.dnc.buendeal.core.databinding.ItemMyReviewBinding
import com.dnc.buendeal.core.extentions.loadImage

class MyReviewsAdapterDelegate(
    context: Context,
) : AdapterDelegate(context) {

    override fun onCreateViewHolder(parent: ViewGroup): ReviewViewHolder =
        ReviewViewHolder(
            ItemMyReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as ReviewItemModel
        (viewHolder as ReviewViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is ReviewItemModel

    override fun getViewType(): Int = R.layout.item_my_review

    class ReviewViewHolder(
        private val binding: ItemMyReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val adapter = ReviewsPhotoAdapter()

        init {
            binding.prodReviewPhotoRecyclerView.adapter = adapter
            binding.prodReviewPhotoRecyclerView.addItemDecoration(PhotoReviewDecorator())
        }

        fun bind(item: ReviewItemModel) {

            adapter.submitList(item.photos)

            binding.apply {
                item.productItemModel?.let {
                    productTitleTv.text = it.title
                    itemPhotoProduct.loadImage(it.imageResource)
                }
                prodReviewRating.setStars(item.stars)
                prodReviewDate.text =
                    binding.root.resources.getString(R.string.review_date, item.date)
                prodReviewComment.text = item.comment
            }
        }
    }
}
