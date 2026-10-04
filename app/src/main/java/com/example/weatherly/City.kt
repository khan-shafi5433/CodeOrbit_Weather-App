package com.example.weatherly

data class City(
    val id: Int,
    val name: String,
    val country: String,

    // Current weather
    val currentTempC: Int,
    val feelsLikeTempC: Int,
    val condition: String,
    val icon: String,
    val humidityPercent: Int,
    val windSpeedKmh: Int,
    val sunriseTime: String,
    val sunsetTime: String,
    val summary: String,

    // 5-day forecast
    val forecast: List<DailyForecast>
)