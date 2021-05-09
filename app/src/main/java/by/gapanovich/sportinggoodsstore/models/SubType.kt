package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class SubType(
    @SerializedName("id_subtype")
    val idSubtype: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("id_type")
    val idType: Int
)