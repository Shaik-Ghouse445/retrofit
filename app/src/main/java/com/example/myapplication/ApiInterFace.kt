package com.example.myapplication

import MyData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterFace {
    @GET("products")
    fun getproductedata(): Call<MyData>


}