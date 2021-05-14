package by.gapanovich.sportinggoodsstore.repository

import by.gapanovich.sportinggoodsstore.api.RetrofitInstance
import by.gapanovich.sportinggoodsstore.models.Order
import by.gapanovich.sportinggoodsstore.models.Product
import by.gapanovich.sportinggoodsstore.models.SubType
import by.gapanovich.sportinggoodsstore.models.Type
import retrofit2.Response

class Repository {
    suspend fun getTypes(): Response<List<Type>> {
        return RetrofitInstance.api.getTypes()
    }

    suspend fun getSubTypes(): Response<List<SubType>> {
        return RetrofitInstance.api.getSubTypes()
    }

    suspend fun getSpecificType(typeId: Int): Response<Type> {
        return RetrofitInstance.api.getSpecificType(typeId)
    }

    suspend fun getSpecificSubType(subTypeId: Int): Response<SubType> {
        return RetrofitInstance.api.getSpecificSubType(subTypeId)
    }

    suspend fun getSpecificSubTypes(typeId: Int): Response<List<SubType>> {
        return RetrofitInstance.api.getSpecificSubTypes(typeId)
    }

    suspend fun getProduct(productId: Int): Response<Product> {
        return RetrofitInstance.api.getProduct(productId)
    }

    suspend fun getSpecificProducts(subTypeId: Int): Response<List<Product>> {
        return RetrofitInstance.api.getSpecificProducts(subTypeId)
    }

    suspend fun getProducts(): Response<List<Product>> {
        return RetrofitInstance.api.getProducts()
    }

    suspend fun createOrder(order: Order): Response<Order> {
        return RetrofitInstance.api.createOrder(order)
    }
}