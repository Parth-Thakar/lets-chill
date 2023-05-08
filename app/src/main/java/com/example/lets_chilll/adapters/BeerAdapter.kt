package com.example.lets_chilll.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lets_chilll.R
import com.example.lets_chilll.databinding.BeerItemRecyclerviewBinding
import com.example.lets_chilll.models.Beers
import com.example.lets_chilll.models.BeersItem
import com.example.lets_chilll.ui.fragments.HomeFragmentDirections
import com.example.lets_chilll.ui.fragments.SavedFragmentDirections
// Adapter class for the recyclerview used in the application.
class BeerAdapter(val context: Context, var list: Beers, private val listner: Share, val fragmentType: String) :
    RecyclerView.Adapter<BeerAdapter.BeerListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerListViewHolder {
        return BeerListViewHolder(
            LayoutInflater.from(context).inflate(R.layout.beer_item_recyclerview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BeerListViewHolder, position: Int) {
        val item = list[position]

        //setting up the title and the price of recyclerview item
        holder.binding.beerName.text = item.name
        holder.binding.beerTagline.text = item.tagline
        holder.binding.beerYear.text = "Year : " + item.first_brewed

        if (position % 2 != 0) {
            holder.binding.detailCardSection.setBackgroundColor(context.getColor(R.color.black))
            holder.binding.imageCardSection.setBackgroundColor(context.getColor(R.color.black))
            holder.binding.beerName.setTextColor(context.getColor(R.color.white))
            holder.binding.beerTagline.setTextColor(context.getColor(R.color.white))
        } else {
            holder.binding.detailCardSection.setBackgroundColor(context.getColor(R.color.white))
            holder.binding.beerName.setTextColor(context.getColor(R.color.black))
            holder.binding.beerTagline.setTextColor(context.getColor(R.color.black))
        }

        //setting up the imageView of recyclerview product item with lazy loading
        Glide.with(context)
            .load(item.image_url)
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.beerImage)

        // logic to check which fragment is calling the ProductDetailFragment so that it can navigate using the direction
        // class of that fragment generated in build.
        holder.itemView.setOnLongClickListener {
            if(fragmentType == "home")
            {
                Navigation.findNavController(it).navigate(
                    HomeFragmentDirections.actionHomeFragmentToBottomSheetFragment(item)
                )
            }
            else
            {
                Navigation.findNavController(it).navigate(
                    SavedFragmentDirections.actionSavedFragmentToBottomSheetFragment(item)
                )
            }
            return@setOnLongClickListener false
        }
        // share button implementation using listner using whatsapp
        holder.binding.shareWhatsapp.setOnClickListener {
            listner.shareBeer(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class BeerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = BeerItemRecyclerviewBinding.bind(view)
    }

    // listner interface for the share button
    interface Share {
        fun shareBeer(item: BeersItem)
    }


}