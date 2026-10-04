package com.example.weatherly

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ForecastActivity : AppCompatActivity() {

    private lateinit var textViewForecastTitle: TextView
    private lateinit var textViewForecastTitleBar: TextView
    private lateinit var recyclerViewForecast: RecyclerView
    private lateinit var imageViewBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        textViewForecastTitle = findViewById(R.id.textViewForecastTitle)
        textViewForecastTitleBar = findViewById(R.id.textViewForecastTitleBar)
        recyclerViewForecast = findViewById(R.id.recyclerViewForecast)
        imageViewBack = findViewById(R.id.imageViewBack)

        // Back button
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val cityId = intent.getIntExtra("CITY_ID", 1)
        val city = SampleWeatherData.getCityById(cityId)

        if (city != null) {
            textViewForecastTitleBar.text = "${city.name} Forecast"
            textViewForecastTitle.text = "5-Day Forecast for ${city.name}"

            setupRecyclerView(city.forecast)
        } else {
            textViewForecastTitleBar.text = "Forecast"
            textViewForecastTitle.text = "5-Day Forecast"
            setupRecyclerView(emptyList())
        }
    }

    private fun setupRecyclerView(forecast: List<DailyForecast>) {
        recyclerViewForecast.layoutManager = LinearLayoutManager(this)
        recyclerViewForecast.adapter = ForecastAdapter(forecast)
    }
}