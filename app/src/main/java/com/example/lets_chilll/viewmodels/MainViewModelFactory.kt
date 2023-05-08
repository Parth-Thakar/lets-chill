package com.example.lets_chilll.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lets_chilll.repository.BeerRepository
import java.security.PrivilegedAction

// mainviewmodel factory class to create the paramitarized constructor of the ViewModel object.
class MainViewModelFactory(private val beerRepository: BeerRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(beerRepository) as T
    }
}