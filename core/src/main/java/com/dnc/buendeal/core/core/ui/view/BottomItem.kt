package com.dnc.buendeal.core.core.ui.view

import com.dnc.buendeal.core.R

enum class BottomItem(
    val rootId: Int,
    val viewId: Int,
    val iconResDefault: Int,
    var iconResActive: Int,
    val title: Int,
    val colorTitleDefault: Int,
    var colorTitleActive: Int,
    var openInner: OpenFrom? = null,
    var params: Any? = null
) {
    HOME(
        1,
        R.id.ivHome,
        R.drawable.ic_home_def_navbar,
        R.drawable.ic_home_check_navbar,
        R.string.home,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    CATALOG(
        2,
        R.id.ivCatalog,
        R.drawable.ic_elements_def_navbar,
        R.drawable.ic_elements_check_navbar,
        R.string.catalog,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    WISHLIST(
        3,
        R.id.ivWishlist,
        R.drawable.ic_heart_def_navbar,
        R.drawable.ic_heart_check_navbar,
        R.string.wishlist,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    CART(
        4,
        R.id.ivCart,
        R.drawable.ic_shopping_cart_btn_unchecked,
        R.drawable.ic_cart_check_navbar,
        R.string.cart,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    PROFILE(
        5,
        R.id.ivProfile,
        R.drawable.ic_user_def_navbar,
        R.drawable.ic_user_check_navbar,
        R.string.profile,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    PRODUCTS(
        6,
        R.id.ivProducts,
        R.drawable.ic_shopping_bag,
        R.drawable.ic_shopping_bag_check_navbar,
        R.string.products,
        R.color.dark_gray_2,
        R.color.blue_3
    ),
    COMPARES(
        7,
        R.id.ivCompares,
        R.drawable.ic_scales_def_navbar,
        R.drawable.ic_scales_check_navbar,
        R.string.compares,
        R.color.dark_gray_2,
        R.color.blue_3
    );

    companion object {
        fun findByViewID(viewId: Int): BottomItem =
            values().firstOrNull { it.viewId == viewId } ?: HOME
    }
}

enum class OpenFrom {
    GO_TO_BOARD
}
