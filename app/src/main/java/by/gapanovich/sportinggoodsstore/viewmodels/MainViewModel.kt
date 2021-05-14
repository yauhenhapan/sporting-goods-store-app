package by.gapanovich.sportinggoodsstore.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.gapanovich.sportinggoodsstore.models.Order
import by.gapanovich.sportinggoodsstore.models.Product
import by.gapanovich.sportinggoodsstore.models.SubType
import by.gapanovich.sportinggoodsstore.models.Type
import by.gapanovich.sportinggoodsstore.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    var type: MutableLiveData<Response<Type>> = MutableLiveData()
    var subType: MutableLiveData<Response<SubType>> = MutableLiveData()
    var product: MutableLiveData<Response<Product>> = MutableLiveData()
    var order: MutableLiveData<Response<Order>> = MutableLiveData()

    var types: MutableLiveData<Response<List<Type>>> = MutableLiveData()
    var subTypes: MutableLiveData<Response<List<SubType>>> = MutableLiveData()
    var products: MutableLiveData<Response<List<Product>>> = MutableLiveData()

    var specificSubTypes: MutableLiveData<Response<List<SubType>>> = MutableLiveData()
    var specificProducts: MutableLiveData<Response<List<Product>>> = MutableLiveData()

    fun getTypes() {
        viewModelScope.launch {
            val response = repository.getTypes()
            types.value = response
        }
    }

    fun getSubTypes() {
        viewModelScope.launch {
            val response = repository.getSubTypes()
            subTypes.value = response
        }
    }

    fun getSpecificType(typeId: Int) {
        viewModelScope.launch {
            val response = repository.getSpecificType(typeId)
            type.value = response
        }
    }

    fun getSpecificSubType(subTypeId: Int) {
        viewModelScope.launch {
            val response = repository.getSpecificSubType(subTypeId)
            subType.value = response
        }
    }

    fun getSpecificSubTypes(typeId: Int) {
        viewModelScope.launch {
            val response = repository.getSpecificSubTypes(typeId)
            specificSubTypes.value = response
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            val response = repository.getProducts()
            products.value = response
        }
    }

    fun getSpecificProducts(subTypeId: Int) {
        viewModelScope.launch {
            val response = repository.getSpecificProducts(subTypeId)
            specificProducts.value = response
        }
    }

    fun getSpecificProduct(productId: Int) {
        viewModelScope.launch {
            val response = repository.getProduct(productId)
            product.value = response
        }
    }

    fun createOrder(customOrder: Order) {
        viewModelScope.launch {
            val response = repository.createOrder(customOrder)
            order.value = response
        }
    }
}