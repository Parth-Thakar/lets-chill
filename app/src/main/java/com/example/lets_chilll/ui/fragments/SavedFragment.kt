package com.example.lets_chilll.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lets_chilll.adapters.BeerAdapter
import com.example.lets_chilll.databinding.FragmentSavedBinding
import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.viewmodels.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SavedFragment : Fragment(), BeerAdapter.Share {

    private lateinit var binding : FragmentSavedBinding
    private lateinit var watchList : ArrayList<String>
    private lateinit var watchlistItem : Beers

    // fetching the same mainViewModel instance from the activity.
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(layoutInflater)

        watchlistItem = Beers()
        watchList = ArrayList()

        // calling the read data method to store the shared prefrence data inside the watchlist
        readData()

        // observing the data and from liveData and checking which titles is actually matching with the titles stored in shared prefrence
        mainViewModel.beers.observe(viewLifecycleOwner) {
            for(watchData in watchList){
                for(item in it){
                    if(watchData == item.name)
                    {
                        watchlistItem.add(item)
                    }
                }
            }
        }

        // setting up the reyclerView
        binding.savedProductListRecyclerView.setHasFixedSize(true)
        binding.savedProductListRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        binding.savedProductListRecyclerView.adapter = BeerAdapter(requireContext(),watchlistItem,this,"saved")
        binding.spinKitViewSaved.visibility = GONE

        return binding.root
    }



    // simple process to reading the data from the sharedprefrences.
    private fun readData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("watchList", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchList", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        watchList = gson.fromJson(json, type)
    }

    override fun shareBeer(item: BeersItem) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this Beer : ${item.name} \n Tagline : ${item.tagline} \n Description : ${item.description}"
            )
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        }
        catch (exp : java.lang.Exception)
        {
            Toast.makeText(requireContext(),"Whatsapp is not installed ! Using another application",
                Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this Beer : ${item.name} \n Tagline : ${item.tagline} \n Description : ${item.description}"
            )
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

}