package com.dnc.buendeal.core.base.recycler

import androidx.recyclerview.widget.DiffUtil

class DefaultDiffItemCallback<DataType> : DiffUtil.ItemCallback<DataType>() {
    override fun areItemsTheSame(oldItem: DataType, newItem: DataType): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: DataType, newItem: DataType): Boolean = false
}
