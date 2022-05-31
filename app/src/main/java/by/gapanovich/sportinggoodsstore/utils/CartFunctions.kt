package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.CartItem

interface CartFunctions {
    fun addToCart(item: CartItem)
    fun removeFromCart(userMail: String, keyProduct: String)
}