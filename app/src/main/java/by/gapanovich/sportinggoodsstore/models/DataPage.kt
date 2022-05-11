package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class DataPage(
    @SerializedName("last")
    val lastPageNumber: Int
)
