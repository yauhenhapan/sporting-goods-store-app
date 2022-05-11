package by.gapanovich.sportinggoodsstore.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OnlinerRetrofit {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://catalog.onliner.by/sdapi/catalog.api/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}