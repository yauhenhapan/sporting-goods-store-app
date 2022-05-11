package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.ProductCatalog

interface CheckArray {
    fun checkArraySize(array: MutableList<ProductCatalog>)
}