package com.example.lets_chilll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.lets_chilll.repository.BeerRepository
import com.example.lets_chilll.retrofit.BeerApi
import com.example.lets_chilll.retrofit.BeerApiHellper
import com.example.lets_chilll.viewmodels.MainViewModel
import com.example.lets_chilll.viewmodels.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val beerapi  = BeerApiHellper.getInstance().create(BeerApi::class.java)


//        GlobalScope.launch {
//            val result = beerapi.getBeers()
//            if(result!=null)
//            {
//                Log.e("beer",result.body().toString())
//            }
//        }

        val beerApiService = BeerApiHellper.getInstance().create(BeerApi::class.java)
        val repository = BeerRepository(beerApiService)

        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)


        mainViewModel.beers.observe(this){
            Log.e("parth","${it.toString()}")
        }


    }
}