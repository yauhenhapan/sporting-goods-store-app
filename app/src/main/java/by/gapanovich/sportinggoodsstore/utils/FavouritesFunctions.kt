package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.FavouriteItem

interface FavouritesFunctions {
    fun addToFavourites(item: FavouriteItem)
    fun removeFromFavourites(userMail: String, keyProduct: String)
}