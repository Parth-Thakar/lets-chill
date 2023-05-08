package com.example.lets_chilll.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lets_chilll.adapters.BeerAdapter
import com.example.lets_chilll.databinding.FragmentHomeBinding
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.viewmodels.MainViewModel

class HomeFragment : Fragment(), BeerAdapter.Share {

    private lateinit var binding: FragmentHomeBinding
    // fetching the same mainViewModel instance from the activity.
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.productListRecyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        binding.productListRecyclerView.layoutManager = layoutManager

        mainViewModel.beers.observe(viewLifecycleOwner){
            binding.productListRecyclerView.adapter = BeerAdapter(requireContext(),it,this,"home")
            binding.spinKitViewHome.visibility = GONE
        }

        return binding.root
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
            Toast.makeText(requireContext(),"Whatsapp is not installed ! Using another application",Toast.LENGTH_SHORT).show()
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