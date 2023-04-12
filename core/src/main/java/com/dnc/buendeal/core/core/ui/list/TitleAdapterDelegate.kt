package com.dnc.buendeal.core.core.ui.list

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.RecyclerView
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.AdapterDelegate
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.base.recycler.RecyclerDelegationAdapter
import com.dnc.buendeal.core.core.ui.model.PopupItemType
import com.dnc.buendeal.core.core.ui.model.PopupWindowItemModel
import com.dnc.buendeal.core.core.ui.model.TitleItemModel
import com.dnc.buendeal.core.databinding.ItemTitleBinding
import com.dnc.buendeal.core.extentions.dp

class TitleAdapterDelegate(
    context: Context,
    private val backBtnCallback: () -> Unit,
    private val plusBtnCallback: (() -> Unit?)? = null,
    private val controlsBtnCallback: ((PopupItemType) -> Unit?)? = null
) : AdapterDelegate(context) {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TitleViewHolder(binding, context)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ItemModel) {
        val currentItem = item as TitleItemModel
        (viewHolder as TitleViewHolder).bind(
            currentItem,
            backBtnCallback,
            plusBtnCallback,
            controlsBtnCallback
        )
    }

    override fun isForViewType(item: ItemModel, position: Int): Boolean = item is TitleItemModel

    override fun getViewType(): Int = R.layout.item_title

    class TitleViewHolder(private val binding: ItemTitleBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        private val controlsPopup by lazy {
            PopupWindow(
                (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                    R.layout.popup_window_comparison,
                    null
                ),
                272.dp,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                isOutsideTouchable = true
                isFocusable = true
                elevation = 15f
                setOnDismissListener { binding.controlsIv.isSelected = false }
            }
        }

        fun bind(
            item: TitleItemModel,
            backBtnCallback: () -> Unit,
            plusBtnCallback: (() -> Unit?)?,
            controlsBtnCallback: ((PopupItemType) -> Unit?)?
        ) {

            if (item.isControlsButtonVisible) {
                binding.controlsIv.visibility = View.VISIBLE
                binding.controlsIv.setOnClickListener {
                    binding.controlsIv.isSelected = true

                    val controlRecycler =
                        controlsPopup.contentView.findViewById<RecyclerView>(R.id.controlRecycler)
                    val adapter = RecyclerDelegationAdapter(context)
                    adapter.addDelegate(
                        PopupWindowAdapterDelegate(context) {
                            controlsBtnCallback?.invoke(it.type)
                            controlsPopup.dismiss()
                        }
                    )
                    controlRecycler.adapter = adapter
                    adapter.setItems(
                        listOf(
                            PopupWindowItemModel(
                                PopupItemType.ADD_PRODUCTS_TO_WISHLIST
                            ),
                            PopupWindowItemModel(
                                PopupItemType.REMOVE_ALL
                            )
                        )
                    )

                    controlsPopup.showAsDropDown(
                        binding.titleCl,
                        binding.titleCl.width - controlsPopup.width - 8.dp,
                        -(binding.titleCl.height - binding.titleCl.height + 8.dp),
                        Gravity.START
                    )
                }
            } else {
                binding.controlsIv.visibility = View.GONE
            }

            if (item.isBackButtonVisible) {
                binding.backBtn.visibility = View.VISIBLE
                binding.backBtn.setOnClickListener { backBtnCallback() }
            } else {
                binding.backBtn.visibility = View.GONE
            }

            binding.titleTv.text = item.title
            if (item.isPlusButtonVisible) {
                binding.plusBtn.visibility = View.VISIBLE
                binding.plusBtn.setOnClickListener { plusBtnCallback?.invoke() }
            } else {
                binding.plusBtn.visibility = View.GONE
            }
        }
    }
}
