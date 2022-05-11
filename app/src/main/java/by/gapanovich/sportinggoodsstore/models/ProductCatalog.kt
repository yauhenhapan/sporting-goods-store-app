package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductCatalog (
    @SerializedName("id")
    val id: Int,
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val img: ProductImage,
    @SerializedName("prices")
    val prices: Prices?,
    @SerializedName("full_name")
    val fullName: String,
    var isFavouriteBtnFilled: Boolean = false
): Serializable


