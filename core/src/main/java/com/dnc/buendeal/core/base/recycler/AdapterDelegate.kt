package com.dnc.buendeal.core.base.recycler

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class AdapterDelegate(
    protected val context: Context
) {

    /**
     * Creates the [RecyclerView.ViewHolder]
     */
    abstract fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Called to bind [viewHolder] to [item]
     */
    abstract fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel)

    open fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        item: ItemModel,
        payloads: MutableList<Any>
    ) {
        onBindViewHolder(viewHolder, item)
    }

    /**
     * Used to determinate, does adapter responsible for [item] with specified [position]
     *
     * @param item
     * @param position
     * @return true if adapter responsible, false if not
     */
    abstract fun isForViewType(item: ItemModel, position: Int): Boolean

    /**
     * Adapter's view time, should be unique across other delegates
     */
    abstract fun getViewType(): Int

    /**
     * Called when viewHolder recycled
     */
    open fun onRecycled(viewHolder: RecyclerView.ViewHolder) {}
}
