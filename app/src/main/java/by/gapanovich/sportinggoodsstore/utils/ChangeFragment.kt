package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.ProductCatalog

interface ChangeFragment {
    fun changeFragment(number: Int)
    fun changeFragment(item: ProductCatalog)
    fun changeFragment(number: Int, string: String)
    fun changeFragment(stringOne: String, stringTwo: String, stringThree: String)
    fun changeFragment(stringOne: String, stringTwo: String, stringThree: String, stringFour: String)
}