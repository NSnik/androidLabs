package com.example.nsnik.app

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query


interface ApiService {
    @GET("/search")
    fun search(@Query("query") query: String): Call<SearchResultModel>
}