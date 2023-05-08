package com.example.lets_chilll.retrofit

import com.example.lets_chilll.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeerApiHellper {
    // retrofit builder helper class to create and build the retrofit instance

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}