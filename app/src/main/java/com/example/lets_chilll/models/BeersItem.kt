package com.example.lets_chilll.models

import java.io.Serializable

data class BeersItem(
    val abv: Double,
    val attenuation_level: Double,
    val boil_volume: BoilVolume,
    val brewers_tips: String,
    val contributed_by: String,
    val description: String,
    val ebc: Int,
    val first_brewed: String,
    val food_pairing: List<String>,
    val ibu: Double,
    val id: Int,
    val image_url: String,
    val ingredients: Ingredients,
    val method: Method,
    val name: String,
    val ph: Double,
    val srm: Double,
    val tagline: String,
    val target_fg: Int,
    val target_og: Double,
    val volume: Volume
) : Serializable
{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(image_url.isNullOrEmpty()){
            result = 31 * result + image_url.hashCode()
        }
        return result
    }
}