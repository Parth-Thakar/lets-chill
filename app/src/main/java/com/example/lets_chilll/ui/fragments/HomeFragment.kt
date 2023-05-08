package com.example.lets_chilll.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lets_chilll.adapters.BeerAdapter
import com.example.lets_chilll.databinding.FragmentHomeBinding
import com.example.lets_chilll.viewmodels.MainViewModel

class HomeFragment : Fragment() {

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
            binding.productListRecyclerView.adapter = BeerAdapter(requireContext(),it)
            binding.spinKitViewHome.visibility = GONE
        }

//        binding.welcomeTextView.setOnClickListener{
//            val fragment = BottomSheetFragment()
//            fragment.show(requireActivity().supportFragmentManager,fragment.tag)
//        }

        return binding.root
    }


}