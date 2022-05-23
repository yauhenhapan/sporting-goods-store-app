package by.gapanovich.sportinggoodsstore.repository

import android.util.Log
import by.gapanovich.sportinggoodsstore.api.OnlinerProductRetrofit
import by.gapanovich.sportinggoodsstore.api.OnlinerRetrofit
import by.gapanovich.sportinggoodsstore.api.RetrofitInstance
import by.gapanovich.sportinggoodsstore.models.*
import retrofit2.Response

class Repository {

    suspend fun getTypes(): Response<List<Type>> {
        return RetrofitInstance.api.getTypes()
    }

    suspend fun getSpecificSubTypes(typeId: Int): Response<List<SubType>> {
        return RetrofitInstance.api.getSpecificSubTypes(typeId)
    }

    suspend fun getSpecificProducts(dictionaryType: String, values: Map<String, String>): Response<Products> {
        return OnlinerRetrofit.api.getSpecificProducts(dictionaryType, values)
    }

    suspend fun createOrder(order: Order): Response<Order> {
        return RetrofitInstance.api.createOrder(order)
    }

    suspend fun getKeyProductsByMail(userMail: String): Response<List<KeyProduct>> {
        return RetrofitInstance.api.getKeyProductsByMail(userMail)
    }

    suspend fun getProduct(key: String): Response<ProductCatalog> {
        return OnlinerProductRetrofit.api.getProduct(key)
    }

    suspend fun getSortingProducts(dictionaryType: String, values: Map<String, String>): Response<Products> {
        return OnlinerRetrofit.api.getSortingProducts(dictionaryType, values)
    }
}