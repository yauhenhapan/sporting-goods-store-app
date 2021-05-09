package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("id_type")
    val idType: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("img_url")
    val imgUrl: String
)
