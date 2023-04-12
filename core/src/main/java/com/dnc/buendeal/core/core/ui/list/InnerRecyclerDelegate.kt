package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.domain.model.SectionId
import com.dnc.buendeal.core.core.ui.model.ProductItemModel
import com.dnc.buendeal.core.core.ui.model.ProductsListItemModel
import com.dnc.buendeal.core.databinding.InnerRecyclerBinding

class InnerRecyclerDelegate(
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
        val currentItem = item as ProductsListItemModel
        (viewHolder as InnerRecyclerViewHolder).bind(currentItem)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is ProductsListItemModel

    override fun getViewType(): Int = R.layout.inner_recycler

    inner class InnerRecyclerViewHolder(
        private val binding: InnerRecyclerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val linear = LinearLayoutManager(
            binding.root.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

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
            binding.innerRecyclerView.addItemDecoration(ProductDecorator())
        }

        fun bind(listProductsItemModel: ProductsListItemModel) {
            val lManager = when (listProductsItemModel.id) {
                SectionId.RECENTLY,
                SectionId.FEATURED,
                SectionId.TOP_DEALS,
                SectionId.BUENDAL_CHOICE,
                SectionId.PEOPLE_CONSIDERED,
                SectionId.ONSALE -> linear
                SectionId.PRODUCTS_GAINING_IN_POPULARITY,
                SectionId.TOPSELLERS -> grid
            }

            binding.innerRecyclerView.layoutManager = lManager
            adapter.setItems(listProductsItemModel.products)
        }
    }
}
