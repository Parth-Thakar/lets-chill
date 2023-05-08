package com.example.lets_chilll.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: BeerRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) { repository.getBeer() }
    }

    // function to expose the randombeerdata livedata.
    fun getRandomBeerData()
    {
        viewModelScope.launch(Dispatchers.IO) { repository.getRandomBeer() }
    }

    // Exposed livedata for the allBeer UI
    val beers : LiveData<Beers>
    get() = repository.beersData

    // Exposed livedata for the randomBeer UI
    val randomBeer : LiveData<Beers>
    get() = repository.randomBeersData

}