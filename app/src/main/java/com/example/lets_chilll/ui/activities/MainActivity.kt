package com.example.lets_chilll.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lets_chilll.R
import com.example.lets_chilll.databinding.ActivityMainBinding
import com.example.lets_chilll.repository.BeerRepository
import com.example.lets_chilll.retrofit.BeerApi
import com.example.lets_chilll.retrofit.BeerApiHellper
import com.example.lets_chilll.viewmodels.MainViewModel
import com.example.lets_chilll.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val beerApiService = BeerApiHellper.getInstance().create(BeerApi::class.java)
        val repository = BeerRepository(beerApiService)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentSpace)

        val navController = navHostFragment!!.findNavController()

        val popupmenu = PopupMenu(this, null)
        popupmenu.inflate(R.menu.bottom_nav_menu)
        binding.bottomBar.setupWithNavController(popupmenu.menu, navController)



    }
}