package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("header")
    val img_url: String
)
