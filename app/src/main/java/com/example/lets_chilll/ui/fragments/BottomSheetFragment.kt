package com.example.lets_chilll.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.lets_chilll.R
import com.example.lets_chilll.databinding.FragmentBottomSheetBinding
import com.example.lets_chilll.models.BeersItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    val item: BottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBottomSheetBinding.inflate(layoutInflater)


        val data = item.beeritem!!


        // Loading the data using glide and adding the Thumbnail util the picture is fully loaded
        Glide.with(requireContext()).load(data.image_url).thumbnail(
            Glide.with(requireContext()).load(R.drawable.spinner)
        ).into(binding.ivItem)

        binding.beerNameScrollView.text = data.name
        binding.beerTaglineScrollView.text = data.tagline

        binding.foodPairingTV.text = data.food_pairing.toString()
        binding.beerDescriptionTV.text = data.description


        // Functionality of Share Button in detail Fragment, Using Implicit Intent
        binding.btnShare.setOnClickListener {
            val drawable: BitmapDrawable = binding.ivItem.drawable as BitmapDrawable
            val bitmap: Bitmap = drawable.bitmap

            val bitmapPath: String = MediaStore.Images.Media.insertImage(
                activity?.applicationContext?.contentResolver,
                bitmap,
                "beerImage",
                null
            )

            val uri: Uri = Uri.parse(bitmapPath)

            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("image/png")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this Beer : ${data.name} \n Tagline : ${data.tagline} \n Description : ${data.description}"
            )
            startActivity(Intent.createChooser(intent, "Share your Beer :) "))


        }


        // function to save the product inside the sharedPrefs
        addToFav(data)

        binding.shareWhatsapp.setOnClickListener {

            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this Beer : ${data.name} \n Tagline : ${data.tagline} \n Description : ${data.description}"
            )
            sendIntent.setType("text/plain")
            sendIntent.setPackage("com.whatsapp")
            startActivity(sendIntent)

        }



        return binding.root
    }

    var watchList: ArrayList<String>? = null
    var isCheck = false

    // addToFav() method
    private fun addToFav(data: BeersItem) {
        readData()
        // Cheking if the user has clicked the heart Icon in the detail fragment then chaging it to red and if did twice
        // then it to return back to normal
        isCheck = if (watchList!!.contains(data.name)) {
            binding.btnFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
            true
        } else {
            binding.btnFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
            false
        }
        // when heart icon is clicked
        binding.btnFav.setOnClickListener {
            isCheck = if (!isCheck) {
                if (!watchList!!.contains(data.name)) {
                    // checking if the the title is not existed in the watchlist then add the title in it.
                    watchList!!.add(data.name)
                }
                // calling the storedata function to add the current updated watchlist to shared prefs
                storeData()
                binding.btnFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red))
                true
            } else {
                // if pressed twice removing the title from the watchlist and updated the list in shared prefrences.
                // and also setting back the color of heart icon back to normal
                binding.btnFav.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
                watchList!!.remove(data.name)
                storeData()
                false
            }
        }
    }

    // simple process to creating and storing data inside the sharedprefrence.
    private fun storeData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("watchList", Context.MODE_PRIVATE)
        val gson = Gson()
        val editor = sharedPreferences.edit()
        val json = gson.toJson(watchList)
        editor.putString("watchList", json)
        Log.e("parth", "${watchList.toString()}")
        editor.apply()
    }

    // simple process to reading the data from the sharedprefrences.
    private fun readData() {
        val sharedPreferences =
            requireContext().getSharedPreferences("watchList", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchList", ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        Log.e("parth", "${watchList.toString()}")
        watchList = gson.fromJson(json, type)
    }
}