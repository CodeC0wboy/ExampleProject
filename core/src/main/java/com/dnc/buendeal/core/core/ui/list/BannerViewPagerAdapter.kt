package com.dnc.buendeal.core.core.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.base.recycler.DefaultDiffItemCallback
import com.dnc.buendeal.core.core.ui.model.BannerItemModel
import com.dnc.buendeal.core.databinding.ItemBannerBinding
import com.dnc.buendeal.core.extentions.loadImage

class BannerViewPagerAdapter :
    ListAdapter<BannerItemModel, BannerViewPagerAdapter.Holder>(DefaultDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    class Holder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BannerItemModel) {
            binding.itemPhotoBanner.loadImage(item.url)
        }
    }
}
