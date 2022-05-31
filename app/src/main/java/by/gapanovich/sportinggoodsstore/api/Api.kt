package by.gapanovich.sportinggoodsstore.api

import android.util.Log
import by.gapanovich.sportinggoodsstore.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("types")
    suspend fun getTypes(): Response<List<Type>>

    @GET("subtypes/{typeId}")
    suspend fun getSpecificSubTypes(
        @Path("typeId") typeId: Int
    ): Response<List<SubType>>

    @GET("{dictionaryType}")
    suspend fun getSpecificProducts(
        @Path("dictionaryType") dictionaryType: String,
        @QueryMap values: Map<String, String>
    ): Response<Products>

    @POST("orders")
    suspend fun createOrder(
        @Body order: Order
    ): Response<Order>

    @GET("orders/{userMail}")
    suspend fun getKeyProductsByMail(
        @Path("userMail") userMail: String
    ): Response<List<KeyProduct>>

    @GET("{key}")
    suspend fun getProduct(
        @Path("key") key: String
    ): Response<ProductCatalog>

    @GET("{dictionaryType}")
    suspend fun getSortingProducts(
        @Path("dictionaryType") dictionaryType: String,
        @QueryMap values: Map<String, String>
    ): Response<Products>

    @POST("carts")
    suspend fun addToCart(
        @Body item: CartItem
    ): Response<CartItem>

    @GET("carts/{userMail}")
    suspend fun getKeyProductsFromCartByMail(
        @Path("userMail") userMail: String
    ): Response<List<KeyProduct>>

    @DELETE("carts/{userMail}/{keyProduct}")
    suspend fun deleteProductFromCartByMailAndKeyProduct(
        @Path("userMail") userMail: String,
        @Path("keyProduct") keyProduct: String
    )

    @POST("favourites")
    suspend fun addToFavourites(
        @Body item: FavouriteItem
    ): Response<FavouriteItem>

    @GET("favourites/{userMail}")
    suspend fun getKeyProductsFromFavouritesByMail(
        @Path("userMail") userMail: String
    ): Response<List<KeyProduct>>

    @DELETE("favourites/{userMail}/{keyProduct}")
    suspend fun deleteProductFromFavouritesByMailAndKeyProduct(
        @Path("userMail") userMail: String,
        @Path("keyProduct") keyProduct: String
    )

    @DELETE("carts/{userMail}/")
    suspend fun deleteAllProductsFromCartByMail(
        @Path("userMail") userMail: String,
    )
}