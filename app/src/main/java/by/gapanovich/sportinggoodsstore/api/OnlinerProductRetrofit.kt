package by.gapanovich.sportinggoodsstore.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OnlinerProductRetrofit {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://catalog.api.onliner.by/products/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}