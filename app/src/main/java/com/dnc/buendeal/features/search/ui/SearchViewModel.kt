package com.dnc.buendeal.features.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnc.buendeal.base.BaseViewModel
import com.dnc.buendeal.core.base.recycler.ItemModel
import com.dnc.buendeal.core.core.domain.model.RecentSearch
import com.dnc.buendeal.core.core.ui.model.BoardItemModel
import com.dnc.buendeal.core.core.ui.model.ProductListItemModel
import com.dnc.buendeal.core.core.ui.model.itemModel
import com.dnc.buendeal.core.extentions.mutable
import com.dnc.buendeal.core.providers.PreferencesProvider
import com.dnc.buendeal.features.search.domain.SearchInteractor
import com.dnc.buendeal.features.search.ui.model.SearchResultsSortFilterItemModel
import com.dnc.buendeal.features.search.ui.model.TitleSearchItemModel
import com.dnc.buendeal.features.search.ui.model.itemModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

class SearchViewModel(
    private val preferencesProvider: PreferencesProvider,
    private val searchInteractor: SearchInteractor
) : BaseViewModel() {

    val listSearchScreen: LiveData<List<ItemModel>> = MutableLiveData()
    val searchResults: LiveData<List<ItemModel>> = MutableLiveData()

    init {
        launch(dispatcher = ioContext) {
            val list = mutableListOf<ItemModel>()
            val recents = searchInteractor.getRecent()
            val categories = searchInteractor.getCategories()
            recents.forEach {
                list.add(it.itemModel())
            }
            list.add(TitleSearchItemModel(0))
            categories.forEach {
                list.add(it.itemModel())
            }

            val boardsList = arrayListOf<BoardItemModel>()

            withContext(context = mainContext) {
                listSearchScreen.mutable().value = list
            }
        }
    }

    fun search(query: CharSequence) {
        launch(dispatcher = ioContext) {
            val tempList = mutableListOf<ItemModel>()
            val recents = searchInteractor.getRecent()
            val categories = searchInteractor.getCategories()

            tempList.addAll(
                recents.filter {
                    it.title.contains(query, true)
                }.map {
                    it.itemModel()
                }
            )

            val tempCategoriesList = categories.filter {
                it.title.contains(query, true)
            }.map {
                it.itemModel()
            }

            if (tempCategoriesList.isNotEmpty())
                tempList.add(TitleSearchItemModel(0))

            tempList.addAll(tempCategoriesList)

            withContext(context = mainContext) {
                if (tempList.isNotEmpty()) {
                    listSearchScreen.mutable().value = tempList
                } else {
                    listSearchScreen.mutable().value = emptyList()
                }
            }
        }
    }

    fun saveSearch(query: String) =
        launch(dispatcher = ioContext) { searchInteractor.saveSearch(RecentSearch(query)) }

    fun performSearch(query: CharSequence, categoryId: String? = null) {
        launch(dispatcher = ioContext) {
            val tempList = mutableListOf<ItemModel>()

            val products =
                searchInteractor.getProducts(query.toString(), categoryId).map { it.itemModel() }

            val sortBy = searchInteractor.getSortBy().itemModel()
            val filters = searchInteractor.getCategoryFilters().map { it.itemModel() }
            val sortFilterModel = SearchResultsSortFilterItemModel(filters, sortBy.criteriaList)

            tempList.add(sortFilterModel)
            tempList.add(ProductListItemModel(products))
            withContext(context = mainContext) {
                searchResults.mutable().value = tempList
            }
        }
    }

    fun wishlistProduct(id: String) {
        searchResults.mutable().value = searchResults.value?.map {
            if (it is ProductListItemModel) {
                ProductListItemModel(
                    it.products.map { product ->
                        if (product.id == id) {
                            product.copy(favorite = true)
                        } else {
                            product
                        }
                    }
                )
            } else {
                it
            }
        }
    }

    fun addProductToCompareList(productId: String) = launch(dispatcher = ioContext) {

    }
}
