package com.example.weatherly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(
    private val cities: List<City>,
    private val favoriteCityIds: Set<Int>,
    private val onCityClicked: (City) -> Unit,
    private val onFavoriteToggled: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    inner class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCityName: TextView = itemView.findViewById(R.id.textViewCityName)
        val textViewCondition: TextView = itemView.findViewById(R.id.textViewCondition)
        val textViewTemperature: TextView = itemView.findViewById(R.id.textViewTemperature)
        val imageViewFavorite: ImageView = itemView.findViewById(R.id.imageViewFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]

        holder.textViewCityName.text = "${city.name}, ${city.country}"
        holder.textViewCondition.text = city.condition
        holder.textViewTemperature.text = "${city.currentTempC}°C"

        val isFavorite = favoriteCityIds.contains(city.id)
        holder.imageViewFavorite.setImageResource(
            if (isFavorite) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )

        holder.itemView.setOnClickListener {
            onCityClicked(city)
        }

        holder.imageViewFavorite.setOnClickListener {
            onFavoriteToggled(city)
        }
    }

    override fun getItemCount(): Int = cities.size
}