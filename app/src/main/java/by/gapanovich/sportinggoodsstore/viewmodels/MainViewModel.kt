package by.gapanovich.sportinggoodsstore.viewmodels

import android.util.Log
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
    var cart: MutableLiveData<Response<CartItem>> = MutableLiveData()
    var favourites: MutableLiveData<Response<FavouriteItem>> = MutableLiveData()

    var types: MutableLiveData<Response<List<Type>>> = MutableLiveData()

    var specificSubTypes: MutableLiveData<Response<List<SubType>>> = MutableLiveData()
    var specificProducts: MutableLiveData<Response<Products>> = MutableLiveData()
    var specificKeyProducts: MutableLiveData<Response<List<KeyProduct>>> = MutableLiveData()

    var sortingProducts: MutableLiveData<Response<Products>> = MutableLiveData()

    var keyProductsFromCart: MutableLiveData<Response<List<KeyProduct>>> = MutableLiveData()
    var keyProdcutsFromFavourites: MutableLiveData<Response<List<KeyProduct>>> = MutableLiveData()

    var productFromCartByKey: MutableLiveData<Response<ProductCatalog>> = MutableLiveData()
    var productFromFavouritesByKey: MutableLiveData<Response<ProductCatalog>> = MutableLiveData()

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

    fun getSortingProducts(dictionaryType: String, values: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getSortingProducts(dictionaryType, values)
            sortingProducts.postValue(response)
        }
    }

    fun addProductToCart(item: CartItem) {
        viewModelScope.launch {
            val response = repository.addToCart(item)
            cart.value = response
        }
    }

    fun addProductToFavourites(item: FavouriteItem) {
        viewModelScope.launch {
            val response = repository.addToFavourites(item)
            favourites.value = response
        }
    }

    fun deleteProductFromCart(userMail: String, keyProduct: String) {
        viewModelScope.launch {
            repository.deleteProductFromCartByMailAndKeyProduct(userMail, keyProduct)
        }
    }

    fun deleteProductFromFavourites(userMail: String, keyProduct: String) {
        viewModelScope.launch {
            repository.deleteProductFromFavouritesByMailAndKeyProduct(userMail, keyProduct)
        }
    }

    fun getKeyProductsFromCart(userMail: String) {
        viewModelScope.launch {
            val response = repository.getKeyProductsFromCartByMail(userMail)
            keyProductsFromCart.value = response
        }
    }

    fun getKeyProductsFromFavourites(userMail: String) {
        viewModelScope.launch {
            val response = repository.getKeyProductsFromFavouritesByMail(userMail)
            keyProdcutsFromFavourites.value = response
        }
    }

    fun getProductFromCartByKey(key: String) {
        viewModelScope.launch {
            val response = repository.getProduct(key)
            productFromCartByKey.value = response
        }
    }

    fun getProductFromFavouritesByKey(key: String) {
        viewModelScope.launch {
            val response = repository.getProduct(key)
            productFromFavouritesByKey.value = response
        }
    }

    fun deleteAllProductsFromCart(userMail: String) {
        viewModelScope.launch {
            repository.deleteAllProductsFromCartByMail(userMail)
        }
    }
}
