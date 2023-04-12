package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.ProductItemModel
import com.dnc.buendeal.core.databinding.ItemProductBinding
import com.dnc.buendeal.core.extentions.dp
import com.dnc.buendeal.core.extentions.loadImage

private const val INDENT = 24

class ProductAdapterDelegate(
    context: Context,
    private val itemListener: (ProductItemModel) -> Unit,
    private val heartBtnListener: (ProductItemModel) -> Unit,
    private val compareBtnListener: (ProductItemModel) -> Unit
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as ProductItemModel
        (viewHolder as ProductViewHolder).bind(currentItem, heartBtnListener, compareBtnListener)
        viewHolder.itemView.setOnClickListener { itemListener.invoke(currentItem) }
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean = item is ProductItemModel

    override fun getViewType(): Int = R.layout.item_product

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val displayWidth = binding.root.resources.displayMetrics.widthPixels

        fun bind(
            product: ProductItemModel,
            heartBtnListener: (ProductItemModel) -> Unit,
            compareBtnListener: (ProductItemModel) -> Unit
        ) {
            binding.apply {
                root.updateLayoutParams {
                    width = displayWidth / 2 - INDENT.dp
                }

                if (product.price == product.newPrice) {
                    oldPrice.visibility = View.GONE
                } else {
                    oldPrice.visibility = View.VISIBLE
                }
                itemPhotoProduct.loadImage(product.imageResource)
                itemTitle.text = product.title
                oldPrice.text = product.price
                itemPrice.text = product.newPrice
                viewRatingStars.setStars(product.stars, product.votes)
                threeStateBtnHeart.isSelected = product.favorite
                threeStateBtnHeart.setOnClickListener {
                    heartBtnListener.invoke(product)
                }
                compareButton.setOnClickListener { compareBtnListener.invoke(product) }
            }
        }
    }
}
