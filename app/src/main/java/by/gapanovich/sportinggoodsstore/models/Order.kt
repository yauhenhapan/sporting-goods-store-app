package by.gapanovich.sportinggoodsstore.models

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_surname")
    val userSurname: String,
    @SerializedName("user_mail")
    val userMail: String,
    @SerializedName("user_phone_number")
    val userPhoneNumber: String,
    @SerializedName("id_product")
    val productId: Int
)
