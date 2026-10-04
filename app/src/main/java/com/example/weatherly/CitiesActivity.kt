package com.example.weatherly

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CitiesActivity : AppCompatActivity() {

    private lateinit var recyclerViewCities: RecyclerView
    private lateinit var imageViewBack: ImageView
    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        prefs = PreferencesManager(this)

        recyclerViewCities = findViewById(R.id.recyclerViewCities)
        imageViewBack = findViewById(R.id.imageViewBack)

        // Back button
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recyclerViewCities.layoutManager = LinearLayoutManager(this)

        val adapter = CityAdapter(
            cities = SampleWeatherData.cities,
            favoriteCityIds = prefs.getFavoriteCityIds(),
            onCityClicked = { city ->
                // When a city is clicked:
                val resultIntent = Intent()
                resultIntent.putExtra("SELECTED_CITY_ID", city.id)
                setResult(RESULT_OK, resultIntent)
                prefs.setSelectedCityId(city.id)
                finish()
            },
            onFavoriteToggled = { city ->
                // Toggle favorite
                prefs.toggleCityFavorite(city.id)
                // Notify adapter to refresh
                recyclerViewCities.adapter?.notifyDataSetChanged()
            }
        )

        recyclerViewCities.adapter = adapter
    }
}