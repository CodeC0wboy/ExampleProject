package com.dnc.buendeal.core.core.ui.model

import androidx.annotation.StringRes
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.data.Image

data class PopupWindowItemModel(
    val type: PopupItemType
) : ItemModel

enum class PopupItemType(val imageRes: Image, @StringRes val title: Int) {
    ADD(Image.ImgRes(R.drawable.comparison_add_control_selector), R.string.add_more_products),
    DELETE(Image.ImgRes(R.drawable.comparison_delete_control_selector), R.string.delete_this_list),
    ADD_PRODUCTS_TO_WISHLIST(
        Image.ImgRes(R.drawable.add_product_to_wish_list_selector),
        R.string.add_products_to_the_wishlist
    ),
    ADD_TO_WISHLIST(
        Image.ImgRes(R.drawable.add_product_to_wish_list_selector),
        R.string.add_to_the_wishlist
    ),
    ADD_TO_COMPARE_LIST(Image.ImgRes(R.drawable.add_to_compare_list), R.string.add_to_compare_list),
    REMOVE_ALL(Image.ImgRes(R.drawable.comparison_delete_control_selector), R.string.remove_all),
    REMOVE_FROM_CART(
        Image.ImgRes(R.drawable.comparison_delete_control_selector),
        R.string.remove_from_cart
    ),
    EDIT_BOARD(Image.ImgRes(R.drawable.wishlist_edit_control_selector), R.string.edit_board),
    DELETE_BOARD(Image.ImgRes(R.drawable.comparison_delete_control_selector), R.string.delete_board),
    EDIT_PRODUCT(Image.ImgRes(R.drawable.wishlist_edit_control_selector), R.string.edit_product),
    DELETE_PRODUCT(Image.ImgRes(R.drawable.comparison_delete_control_selector), R.string.delete_product),
}
