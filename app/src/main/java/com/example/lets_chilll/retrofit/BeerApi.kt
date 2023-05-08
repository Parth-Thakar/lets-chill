package com.example.lets_chilll.retrofit

import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import retrofit2.Response
import retrofit2.http.GET

interface BeerApi {

    //Api End Point to fetch all the beer
    @GET("beers")
    suspend fun getBeers() : Response<Beers>

    // API end points to fetch the random 1 beer data
    @GET("beers/random")
    suspend fun getRandomBeers():Response<Beers>
}