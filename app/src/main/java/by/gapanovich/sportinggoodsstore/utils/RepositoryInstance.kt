package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.ProductCatalog

object RepositoryInstance {
    var cartArray = mutableListOf<ProductCatalog>()
    var favArray = mutableListOf<ProductCatalog>()
    var ordersArray = mutableListOf<ProductCatalog>()
}