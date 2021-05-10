package by.gapanovich.sportinggoodsstore.utils

import androidx.lifecycle.MutableLiveData
import by.gapanovich.sportinggoodsstore.models.Product

object RepositoryInstance {
    var cartArray = mutableListOf<Product>()
    var favArray: MutableLiveData<Product> = MutableLiveData()
}