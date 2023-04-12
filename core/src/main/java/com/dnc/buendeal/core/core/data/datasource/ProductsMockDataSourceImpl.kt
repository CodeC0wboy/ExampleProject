package com.dnc.buendeal.core.core.data.datasource

import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.core.data.response.*
import com.dnc.buendeal.core.core.data.response.filter.FilterSortResponse
import com.dnc.buendeal.core.core.data.response.singleProduct.ColorResponse
import com.dnc.buendeal.core.core.data.response.singleProduct.OptionColorsResponse
import com.dnc.buendeal.core.core.data.response.singleProduct.SingleProductResponse
import com.dnc.buendeal.core.core.domain.data.Image
import com.dnc.buendeal.core.core.domain.model.*
import com.dnc.buendeal.core.core.domain.model.singleProduct.OptionColors
import com.dnc.buendeal.core.core.domain.model.singleProduct.SingleProduct
import com.dnc.buendeal.core.core.domain.model.singleProduct.domain
import com.dnc.buendeal.core.extentions.mutable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductsMockDataSourceImpl : ProductsDataSource {

    private val assignedProductsFlow: StateFlow<List<AssignedProduct>> = MutableStateFlow(arrayListOf())
    private val productsByCategory: StateFlow<List<Product>?> = MutableStateFlow(null)
    private val viewedProductsFlow: StateFlow<List<Product>> = MutableStateFlow(arrayListOf())

    override suspend fun getProductsByCategory(categoryId: String?): List<Product> {
        val productsList = listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors(),
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "4",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "5",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "6",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
        ).map { it.domain() }
        productsByCategory.mutable().value = productsList
        return productsList
    }

    override suspend fun refreshCategoryProducts() {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryProductsFlow(): StateFlow<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFeaturedProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByPrice(
        categoryId: String?,
        upperValue: String?,
        lowerValue: String?
    ): List<Product> {
        val productsList = listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "4",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "5",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "6",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
        ).map { it.domain() }
        productsByCategory.mutable().value = productsList
        return productsList
    }

    override suspend fun getProductsByAppliedFilters(
        categoryId: String?,
        attributesId: List<String>?
    ): List<Product> {
        val productsList = listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "4",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "5",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "6",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
        ).map { it.domain() }
        productsByCategory.mutable().value = productsList
        return productsList
    }

    override suspend fun addProductsToRecentlyViewed(product: Product) {
    }

    override suspend fun getViewedProducts(): List<Product> {
        return listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            )
        ).map { it.domain() }
    }

    override suspend fun getViewedProductsFlow(): StateFlow<List<Product>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSortBy(): FilterSort = FilterSortResponse(
        0,
        listOf(
            SortCriteria.REVELANCE,
            SortCriteria.LOWEST_PRICE,
            SortCriteria.HIGHEST_PRICE,
            SortCriteria.BEST_RATED,
            SortCriteria.MOST_POPULAR,
            SortCriteria.DISCOUNTS,
            SortCriteria.NEWEST,
        )
    ).domain()

    override suspend fun refreshAssignedProduct() {
        assignedProductsFlow.mutable().value = listOf(
            AssignedProduct(id = SectionId.RECENTLY, products = getViewedProducts()),
            AssignedProduct(id = SectionId.FEATURED, products = getViewedProducts())
        )
    }

    override suspend fun getAssignedProductFlow(): StateFlow<List<AssignedProduct>> = assignedProductsFlow

    override suspend fun getRecentlyViewedAssignedProducts(): List<AssignedProduct> {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(productId: String?): SingleProduct = SingleProductResponse(
        id = 0,
        listOf(
            Image.ImgRes(R.drawable.product_image),
            Image.ImgRes(R.drawable.product_image),
            Image.ImgRes(R.drawable.product_image),
            Image.ImgRes(R.drawable.product_image),
            Image.ImgRes(R.drawable.product_image)
        ),
        Product(
            id = "1",
            Image.ImgRes(R.drawable.gyrocopter),
            "Bose Sport Earbuds – True Wireless Bluetoo...",
            5,
            12,
            "$159.00",
            "$129.00",
            "USD",
            getProductsAttributes().map { it.domain() },
            SellerInfoMockResponse(
                Image.ImgRes(R.drawable.gyrocopter),
                "Headphones For All",
                5
            ).domain(),
            DescriptionResponse(
                0,
                "The new AirPods combine intelligent design with breakthrough" +
                    " technology and crystal clear sound. Powered by the new Apple H1 headphone chip," +
                    " AirPods now feature hands-free access to Siri using just your voice. And up to 3" +
                    " hours of talk time on a single charge. \n\n  • Automatically on, automatically connected \n\n" +
                    "  • Easy setup for all your apple devices \n\n  • Quick access to Siri by saying \"Hey Siri\" \n\n" +
                    "  • Double-tap to play or skip forward \n\n  • Charges quickly in the case \n\n  • Case can be charged " +
                    "using the lightning connector \n\n  • Rich, high-quality audio and voice"
            ).domain(),
            ProductMainAttributeDto(
                "",
                listOf(
                    ProductInnerAttributeDto("Brand", "Google"),
                    ProductInnerAttributeDto("Color", "White"),
                    ProductInnerAttributeDto("Manufacturer", "Google inc."),
                    ProductInnerAttributeDto(
                        "Product Dimensions (L x W x H)",
                        "0.7 x .06 x 1.34 Inches"
                    ),
                    ProductInnerAttributeDto("Item Weight", "0.67 ounces"),
                    ProductInnerAttributeDto("Batteries", "Lithium ion (included)"),
                    ProductInnerAttributeDto("Date First Available", "March 20, 2020"),
                )
            ).domain(),
            optionColors = getOptionColors(),
            sellerId = "1.3."
        ),
    ).domain()

    override suspend fun getProductsBySeller(
        sellerId: String,
        sortCriteria: SortCriteria
    ): List<Product> {
        val productsList = listOf(
            ProductMockResponse(
                id = "1",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "2",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            ),
            ProductMockResponse(
                id = "3",
                Image.ImgRes(R.drawable.gyrocopter),
                "Bose Sport Earbuds – True Wireless Bluetoo...",
                5,
                12,
                "$172.00",
                "$152.00",
                "USD",
                description = null,
                specifications = null,
                optionColors = getOptionColors()
            )
        )
        return productsList.map { it.domain() }
    }

    fun getProductsAttributes() = listOf(
        ProductMainAttributeDto(
            "Main",
            mutableListOf(
                ProductInnerAttributeDto("Brand", "Google"),
                ProductInnerAttributeDto("Color", "White"),
                ProductInnerAttributeDto("Manufacturer", "Google inc."),
                ProductInnerAttributeDto(
                    "Product Dimensions (L x W x H)",
                    "0.7 x .06 x 1.34 Inches"
                ),
                ProductInnerAttributeDto("Item Weight", "0.67 ounces"),
                ProductInnerAttributeDto("Batteries", "Lithium ion (included)"),
                ProductInnerAttributeDto("Date First Available", "March 20, 2020"),
            )
        ),
        ProductMainAttributeDto(
            "Display Options",
            mutableListOf(
                ProductInnerAttributeDto("Brand", "Google"),
                ProductInnerAttributeDto("Color", "White"),
                ProductInnerAttributeDto("Manufacturer", "Google inc."),
                ProductInnerAttributeDto(
                    "Product Dimensions (L x W x H)",
                    "0.7 x .06 x 1.34 Inches"
                ),
                ProductInnerAttributeDto("Item Weight", "0.67 ounces"),
                ProductInnerAttributeDto("Batteries", "Lithium ion (included)"),
                ProductInnerAttributeDto("Date First Available", "March 20, 2020"),
            )
        ),
        ProductMainAttributeDto(
            "Features",
            mutableListOf(
                ProductInnerAttributeDto("Brand", "Google"),
                ProductInnerAttributeDto("Color", "White"),
                ProductInnerAttributeDto("Manufacturer", "Google inc."),
                ProductInnerAttributeDto(
                    "Product Dimensions (L x W x H)",
                    "0.7 x .06 x 1.34 Inches"
                ),
                ProductInnerAttributeDto("Item Weight", "0.67 ounces"),
                ProductInnerAttributeDto("Batteries", "Lithium ion (included)"),
                ProductInnerAttributeDto("Date First Available", "March 20, 2020"),
            )
        )
    )

    private fun getOptionColors(): OptionColors = OptionColorsResponse(
        id = 0,
        listOf(
            ColorResponse(1, "Green", "#34A853", "1"),
            ColorResponse(2, "Shade", "#F8F9F9", "2"),
            ColorResponse(3, "Black", "#000000", "3"),
            ColorResponse(4, "Red", "#EA3535", "4"),
        )
    ).domain()
}
