package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("products")
    val productsArray: List<ProductCatalog>,
    @SerializedName("page")
    val page: DataPage
)
