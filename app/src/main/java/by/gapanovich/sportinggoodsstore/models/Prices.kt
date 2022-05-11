package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class Prices(
    @SerializedName("price_min")
    val price: Price?
)
