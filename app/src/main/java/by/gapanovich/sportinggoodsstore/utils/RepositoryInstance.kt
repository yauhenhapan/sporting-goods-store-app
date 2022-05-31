package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.KeyProduct
import by.gapanovich.sportinggoodsstore.models.ProductCatalog

object RepositoryInstance {
    var cartArray = mutableListOf<String>()
    var favArray = mutableListOf<String>()
    var ordersArray = mutableListOf<String>()
}