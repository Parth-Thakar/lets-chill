package com.example.lets_chilll.retrofit

import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import retrofit2.Response
import retrofit2.http.GET

interface BeerApi {

    @GET("beers")
    suspend fun getBeers() : Response<Beers>

    @GET("beers/random")
    suspend fun getRandomBeers():Response<Beers>
}