package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.dnc.buendeal.core.databinding.ViewBottomSingleProductBinding

class BottomSingleProduct @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    private val binding: ViewBottomSingleProductBinding =
        ViewBottomSingleProductBinding.inflate(LayoutInflater.from(context), this, true)

    var onBuyClick: (() -> Unit)? = null
    var onCompareClick: (() -> Unit)? = null
    var onBasketClick: (() -> Unit)? = null
    var onWishClick: (() -> Unit)? = null

    init {
        binding.singleProdBtnBuy.setOnClickListener {
            onBuyClick?.invoke()
        }
        binding.singleProdIvCompare.setOnClickListener {
            onCompareClick?.invoke()
        }
        binding.singleProdIvCart.setOnClickListener {
            onBasketClick?.invoke()
        }
        binding.singleProdIvWishlist.setOnClickListener {
            onWishClick?.invoke()
        }

        doOnLayout {
            (parent as? ViewGroup)?.clipChildren = false
            clipChildren = false
        }
    }

    fun updateWishlistState(isPressed: Boolean) {
        binding.singleProdIvWishlist.isPressed = isPressed
    }
}
