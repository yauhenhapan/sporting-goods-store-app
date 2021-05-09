package by.gapanovich.sportinggoodsstore.api

import by.gapanovich.sportinggoodsstore.models.SubType
import by.gapanovich.sportinggoodsstore.models.Type
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("types")
    suspend fun getTypes(): Response<List<Type>>

    @GET("subtypes")
    suspend fun getSubTypes(): Response<List<SubType>>

    @GET("type/{typeId}")
    suspend fun getSpecificType(
        @Path("typeId") typeId: Int
    ): Response<Type>

    @GET("subtype/{subTypeId}")
    suspend fun getSpecificSubType(
        @Path("subTypeId") subTypeId: Int
    ): Response<SubType>

    @GET("subtypes/{typeId}")
    suspend fun getSpecificSubTypes(
        @Path("typeId") typeId: Int
    ): Response<List<SubType>>

}