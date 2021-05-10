package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id_product")
    val productId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("id_subtype")
    val subTypeId: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    val isSelected: Boolean = false
)