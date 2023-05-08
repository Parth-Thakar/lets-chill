package com.example.lets_chilll.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lets_chilll.R
import com.example.lets_chilll.databinding.BeerItemRecyclerviewBinding
import com.example.lets_chilll.models.Beers

class BeerAdapter(val context: Context, var list: Beers) : RecyclerView.Adapter<BeerAdapter.BeerListViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.beer_item_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val item = list[position]

        holder.binding.beerName.text = item.name
        holder.binding.beerTagline.text = item.tagline
        holder.binding.beerYear.text = item.first_brewed

        //setting up the imageView of recyclerview product item
        Glide.with(context)
            .load(item.image_url)
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.beerImage)

    }

    override fun getItemCount(): Int {
       return list.size
    }

    inner class BeerListViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var binding = BeerItemRecyclerviewBinding.bind(view)
    }

}