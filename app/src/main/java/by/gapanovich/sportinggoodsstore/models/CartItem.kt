package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class CartItem(
    @SerializedName("user_mail")
    val userMail: String,
    @SerializedName("key_product")
    val productKey: String
)
