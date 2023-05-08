package com.example.lets_chilll.ui.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.lets_chilll.R
import com.example.lets_chilll.databinding.FragmentRandomBinding
import com.example.lets_chilll.databinding.FragmentSavedBinding
import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.viewmodels.MainViewModel
import kotlin.time.measureTime

class RandomFragment : Fragment() {

    private lateinit var binding: FragmentRandomBinding


    // fetching the same mainViewModel instance from the activity.
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentRandomBinding.inflate(layoutInflater)


        var list : Beers

        list = Beers()

        mainViewModel.getRandomBeerData()

        mainViewModel.randomBeer.observe(viewLifecycleOwner){
            list = it
        }

        Handler().postDelayed({
            binding.randomAnimation.visibility = GONE
            binding.randomCard.visibility = VISIBLE
            binding.beerName.text = list[0].name
            binding.beerTagline.text = list[0].tagline
            binding.beerYear.text = list[0].first_brewed
        },2500)


        return binding.root
    }


}