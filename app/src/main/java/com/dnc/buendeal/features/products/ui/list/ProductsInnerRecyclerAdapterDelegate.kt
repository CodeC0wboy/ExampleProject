package com.dnc.buendeal.features.products.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.ui.list.InnerItemDecorator
import com.dnc.buendeal.core.core.ui.list.ProductAdapterDelegate
import com.dnc.buendeal.core.core.ui.model.ProductItemModel
import com.dnc.buendeal.core.core.ui.model.ProductListItemModel
import com.dnc.buendeal.core.databinding.InnerRecyclerBinding

class ProductsInnerRecyclerAdapterDelegate(
    context: Context,
    private val itemListener: (ProductItemModel) -> Unit,
    private val heartBtnListener: (ProductItemModel) -> Unit,
    private val compareBtnListener: (ProductItemModel) -> Unit
) : AdapterDelegate(context) {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = InnerRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InnerRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as ProductListItemModel
        (viewHolder as InnerRecyclerViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is ProductListItemModel

    override fun getViewType(): Int = R.layout.inner_recycler

    inner class InnerRecyclerViewHolder(
        private val binding: InnerRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val grid = GridLayoutManager(binding.root.context, 2)

        private val adapter = RecyclerDelegationAdapter(binding.root.context).apply {
            addDelegate(
                ProductAdapterDelegate(
                    binding.root.context,
                    itemListener,
                    heartBtnListener,
                    compareBtnListener
                )
            )
        }

        init {
            binding.innerRecyclerView.adapter = adapter
            binding.innerRecyclerView.addItemDecoration(InnerItemDecorator())
        }

        fun bind(listProductsItemModel: ProductListItemModel) {
            binding.innerRecyclerView.layoutManager = grid
            adapter.setItems(listProductsItemModel.products)
        }
    }
}
