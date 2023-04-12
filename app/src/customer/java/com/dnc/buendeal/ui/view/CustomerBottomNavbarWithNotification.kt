package com.dnc.buendeal.ui.view

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.doOnLayout
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.navigation.BottomNavbar
import com.dnc.buendeal.core.core.ui.view.BottomItem
import com.dnc.buendeal.core.databinding.ViewCustomerBottomNavbarNotificationBinding
import com.dnc.buendeal.core.extentions.gone
import com.dnc.buendeal.core.extentions.hideKeyboard
import com.dnc.buendeal.core.extentions.visible

class CustomerBottomNavbarWithNotification @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : BottomNavbar, ConstraintLayout(context, attrs) {
    private val binding: ViewCustomerBottomNavbarNotificationBinding =
        ViewCustomerBottomNavbarNotificationBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    private val viewsList = listOf(
        binding.ivHome to binding.tvHome,
        binding.ivCatalog to binding.tvCatalog,
        binding.ivWishlist to binding.tvWishlist,
        binding.ivCart to binding.tvCart,
        binding.ivCompares to binding.tvCompares,
        binding.ivProfile to binding.tvProfile
    )

    override var currentActiveTab = BottomItem.HOME

    override var destinationListener: ((activeBottomItem: BottomItem, commitNow: Boolean) -> Unit)? =
        null

    private fun setActiveTabsColor() {
        BottomItem.HOME.colorTitleActive = R.color.blue_3
        BottomItem.HOME.iconResActive = R.drawable.ic_home_check_navbar_blue
        BottomItem.CATALOG.colorTitleActive = R.color.blue_3
        BottomItem.CATALOG.iconResActive = R.drawable.ic_elements_check_navbar_blue
        BottomItem.WISHLIST.colorTitleActive = R.color.blue_3
        BottomItem.WISHLIST.iconResActive = R.drawable.ic_heart_check_navbar_blue
        BottomItem.CART.colorTitleActive = R.color.blue_3
        BottomItem.CART.iconResActive = R.drawable.ic_cart_check_navbar_blue
        BottomItem.PROFILE.colorTitleActive = R.color.blue_3
        BottomItem.PROFILE.iconResActive = R.drawable.ic_user_check_navbar_blue
        BottomItem.COMPARES.colorTitleActive = R.color.blue_3
        BottomItem.COMPARES.iconResActive = R.drawable.ic_scales_check_navbar_blue
    }

    init {
        setActiveTabsColor()
        setActiveTab(BottomItem.HOME)

        binding.ivHome.setOnClickListener {
            setActiveTab(BottomItem.HOME)
        }
        binding.ivCatalog.setOnClickListener {
            setActiveTab(BottomItem.CATALOG)
        }
        binding.ivWishlist.setOnClickListener {
            setActiveTab(BottomItem.WISHLIST)
        }
        binding.ivCart.setOnClickListener {
            setActiveTab(BottomItem.CART)
        }
        binding.ivProfile.setOnClickListener {
            setActiveTab(BottomItem.PROFILE)
        }
        binding.ivCompares.setOnClickListener {
            setActiveTab(BottomItem.COMPARES)
        }

        binding.tvHome.setOnClickListener {
            setActiveTab(BottomItem.HOME)
        }
        binding.tvCatalog.setOnClickListener {
            setActiveTab(BottomItem.CATALOG)
        }
        binding.tvWishlist.setOnClickListener {
            setActiveTab(BottomItem.WISHLIST)
        }
        binding.tvCart.setOnClickListener {
            setActiveTab(BottomItem.CART)
        }
        binding.tvProfile.setOnClickListener {
            setActiveTab(BottomItem.PROFILE)
        }
        binding.tvCompares.setOnClickListener {
            setActiveTab(BottomItem.COMPARES)
        }

        doOnLayout {
            (parent as? ViewGroup)?.clipChildren = false
            clipChildren = false
        }
    }

    override fun setActiveTab(activeBottomItem: BottomItem) {
        hideKeyboard()
        destinationListener?.invoke(activeBottomItem, true)
        currentActiveTab = activeBottomItem
        viewsList.forEach {
            if (it.first.id == activeBottomItem.viewId) {
                it.first.setImageResource(activeBottomItem.iconResActive)
                it.second.setTextColor(context.getColor(activeBottomItem.colorTitleActive))
            } else {
                it.first.setImageResource(BottomItem.findByViewID(it.first.id).iconResDefault)
                it.second.setTextColor(context.getColor(BottomItem.findByViewID(it.first.id).colorTitleDefault))
            }
        }
    }

    override fun setMarker(newNotification: Boolean, bottomItem: BottomItem) {
        if (newNotification) {
            when (bottomItem) {
                BottomItem.HOME -> binding.dotHome.visible()
                BottomItem.CATALOG -> binding.dotCatalog.visible()
                BottomItem.WISHLIST -> binding.dotWishlist.visible()
                BottomItem.CART -> binding.dotCart.visible()
                BottomItem.PROFILE -> binding.dotProfile.visible()
                BottomItem.COMPARES -> binding.dotCompares.visible()
                else -> {}
            }
        } else {
            when (bottomItem) {
                BottomItem.HOME -> binding.dotHome.gone()
                BottomItem.CATALOG -> binding.dotCatalog.gone()
                BottomItem.WISHLIST -> binding.dotWishlist.gone()
                BottomItem.CART -> binding.dotCart.gone()
                BottomItem.PROFILE -> binding.dotProfile.gone()
                BottomItem.COMPARES -> binding.dotCompares.gone()
                else -> {}
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putInt(SELECTED_TAB_STATE, currentActiveTab.viewId)
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState())
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            val activeTab = state.getInt(SELECTED_TAB_STATE, R.id.ivHome)
            setActiveTab(BottomItem.findByViewID(activeTab))
            super.onRestoreInstanceState(state.getParcelable(SUPER_STATE))
        }
    }

    companion object {
        private const val SELECTED_TAB_STATE = "selectedTab"
        private const val SUPER_STATE = "superState"
    }
}
