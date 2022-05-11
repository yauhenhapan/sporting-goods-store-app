package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class SubType(
    @SerializedName("id_subtype")
    val idSubtype: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("id_type")
    val idType: Int,
    @SerializedName("img_url")
    val imgUrl: String,
    @SerializedName("name_dictionary")
    val dictionarySubType: String,
    @SerializedName("key_category")
    val keyCategory: String,
    @SerializedName("key_category_operation")
    val keyCategoryOperation: String
)