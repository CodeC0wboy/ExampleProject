package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.BannerListItemModel
import com.dnc.buendeal.core.databinding.ItemBannerViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class BannerAdapterDelegate(context: Context) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemBannerViewPagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        (viewHolder as BannerViewHolder).bind(item as BannerListItemModel)
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean =
        item is BannerListItemModel

    override fun getViewType(): Int = R.layout.item_banner_view_pager

    class BannerViewHolder(private val binding: ItemBannerViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val adapter = BannerViewPagerAdapter()

        fun bind(item: BannerListItemModel) {
            binding.bannerViewPager.adapter = adapter
            adapter.submitList(item.banners)

            TabLayoutMediator(binding.bannerTabLayout, binding.bannerViewPager) { tab, _ ->
                tab.icon = ResourcesCompat.getDrawable(
                    binding.root.resources,
                    R.drawable.tab_selector,
                    null
                )
            }.attach()
        }
    }
}
