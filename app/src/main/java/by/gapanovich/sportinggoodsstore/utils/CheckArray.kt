package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.Product

interface CheckArray {
    fun checkArraySize(array: MutableList<Product>)
}