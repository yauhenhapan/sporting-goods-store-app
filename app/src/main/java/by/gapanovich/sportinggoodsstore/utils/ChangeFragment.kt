package by.gapanovich.sportinggoodsstore.utils

import by.gapanovich.sportinggoodsstore.models.ProductCatalog

interface ChangeFragment {
    fun changeFragment(number: Int)
    fun changeFragment(item: ProductCatalog)
    fun changeFragment(string: String, map: HashMap<String, String>)
    fun changeFragment(number: Int, string: String)
    fun changeFragment(number: Int, stringOne: String, stringTwo: String)
    fun changeFragment(stringOne: String, stringTwo: String, stringThree: String)
    fun changeFragment(stringOne: String, stringTwo: String, stringThree: String, stringFour: String)
    fun changeFragment(stringOne: String, stringTwo: String, stringThree: String, stringFour: String, stringFive: String)
}