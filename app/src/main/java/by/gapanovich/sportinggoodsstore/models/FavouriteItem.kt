package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class FavouriteItem(
    @SerializedName("user_mail")
    val userMail: String,
    @SerializedName("key_product")
    val productKey: String
)
