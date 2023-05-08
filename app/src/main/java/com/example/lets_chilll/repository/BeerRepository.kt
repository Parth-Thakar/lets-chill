package com.example.lets_chilll.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.retrofit.BeerApi

class BeerRepository(private val beerApi : BeerApi) {


    private val beerLiveData = MutableLiveData<Beers>()

    val beersData : LiveData<Beers>
    get() = beerLiveData

    private val randomBeerLiveData = MutableLiveData<Beers>()

    val randomBeersData : LiveData<Beers>
        get() = randomBeerLiveData

    suspend fun getBeer(){
        val result = beerApi.getBeers()
        if(result?.body() != null)
        {
            beerLiveData.postValue(result.body())
        }
    }

    suspend fun getRandomBeer()
    {
        val randomBeer = beerApi.getRandomBeers()
        if(randomBeer?.body()!=null)
        {
            randomBeerLiveData.postValue(randomBeer.body())
        }
    }
}