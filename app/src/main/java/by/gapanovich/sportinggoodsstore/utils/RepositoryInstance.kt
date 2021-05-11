package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.Product

object RepositoryInstance {
    var cartArray = mutableListOf<Product>()
    var favArray = mutableListOf<Product>()
}