package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.ReviewItemModel
import com.dnc.buendeal.core.databinding.ItemSingleReviewBinding

class ReviewAdapterDelegate(
    context: Context,
) : AdapterDelegate(context) {

    override fun onCreateViewHolder(parent: ViewGroup): ReviewViewHolder =
        ReviewViewHolder(
            ItemSingleReviewBinding.inflate(
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

    override fun getViewType(): Int = R.layout.item_single_review

    class ReviewViewHolder(
        private val binding: ItemSingleReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        val adapter = ReviewsPhotoAdapter()

        init {
            binding.prodReviewPhotoRecyclerView.adapter = adapter
            binding.prodReviewPhotoRecyclerView.addItemDecoration(PhotoReviewDecorator())
        }

        fun bind(item: ReviewItemModel) {

            adapter.submitList(item.photos)

            binding.apply {
                productReviewUsername.text = item.username
                prodReviewRating.setStars(item.stars)
                prodReviewDate.text =
                    binding.root.resources.getString(R.string.review_date, item.date)
                prodReviewComment.text = item.comment
            }
        }
    }
}
