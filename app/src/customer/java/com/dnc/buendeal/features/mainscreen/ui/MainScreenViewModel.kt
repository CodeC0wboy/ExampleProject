package com.dnc.buendeal.features.mainscreen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnc.buendeal.base.BaseViewModel
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.ui.model.*
import com.dnc.buendeal.core.extentions.mutable
import com.dnc.buendeal.features.mainscreen.domain.MainScreenInteractor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

private const val ID_HOME_CATEGORY = "1"

class MainScreenViewModel(
    private val mainScreenInteractor: MainScreenInteractor
) : BaseViewModel() {

    val listProducts: LiveData<List<ItemModel>> = MutableLiveData()
    val delegateList = mutableListOf<ItemModel>()

    var products: List<ProductItemModel> = emptyList()

    init {
        refreshItems()
    }

    fun refreshItems() {
        launch(dispatcher = ioContext) {
            mainScreenInteractor.refreshAssignedProduct()
            mainScreenInteractor.getAssignedProductFlow().collect { classifiedProducts ->
                val titleSections = mainScreenInteractor.getTitleSection()

                val banners = mainScreenInteractor.getBannersByCategory(ID_HOME_CATEGORY)

                val list = mutableListOf<ItemModel>()
                list.add(BannerListItemModel(0, banners.map { it.itemModel() }))
                list.add(SearchItemModel(0))

                titleSections.forEach { section ->
                    list.add(section.itemModel())
                    val products = classifiedProducts
                        .firstOrNull { it.id == section.id }
                        ?.products
                        ?.map { it.itemModel() }
                        ?: emptyList()
                    list.add(ProductsListItemModel(section.id, products))
                }

                withContext(context = mainContext) {
                    listProducts.mutable().value = list
                }
            }
        }
    }

    fun updateStateProduct(id: String) =
        launch(dispatcher = ioContext) { mainScreenInteractor.updateStateProduct(id) }

    fun addProductToCompareList(productId: String) = launch(dispatcher = ioContext) {

    }
}
