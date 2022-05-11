package by.gapanovich.sportinggoodsstore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.gapanovich.sportinggoodsstore.models.*
import by.gapanovich.sportinggoodsstore.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var type: MutableLiveData<Response<Type>> = MutableLiveData()
    var order: MutableLiveData<Response<Order>> = MutableLiveData()
    var productCatalog: MutableLiveData<Response<ProductCatalog>> = MutableLiveData()

    var types: MutableLiveData<Response<List<Type>>> = MutableLiveData()

    var specificSubTypes: MutableLiveData<Response<List<SubType>>> = MutableLiveData()
    var specificProducts: MutableLiveData<Response<Products>> = MutableLiveData()
    var specificKeyProducts: MutableLiveData<Response<List<KeyProduct>>> = MutableLiveData()

    var additionalProducts: MutableLiveData<Response<Products>> = MutableLiveData()

    fun getTypes() {
        viewModelScope.launch {
            val response = repository.getTypes()
            types.value = response
        }
    }

    fun getSpecificSubTypes(typeId: Int) {
        viewModelScope.launch {
            val response = repository.getSpecificSubTypes(typeId)
            specificSubTypes.value = response
        }
    }

    fun getSpecificProducts(dictionaryType: String, values: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getSpecificProducts(dictionaryType, values)
            specificProducts.value = response
        }
    }

    fun createOrder(customOrder: Order) {
        viewModelScope.launch {
            val response = repository.createOrder(customOrder)
            order.value = response
        }
    }

    fun getKeyProductsFromOrders(userMail: String) {
        viewModelScope.launch {
            val response = repository.getKeyProductsByMail(userMail)
            specificKeyProducts.value = response
        }
    }

    fun getProduct(key: String) {
        viewModelScope.launch {
            val response = repository.getProduct(key)
            productCatalog.value = response
        }
    }

    fun getAdditionalProducts(dictionaryType: String, values: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getSpecificProducts(dictionaryType, values)
            additionalProducts.value = response
        }
    }
}