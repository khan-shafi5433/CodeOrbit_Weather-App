package com.example.weatherly

data class DailyForecast(
    val day: String,          // e.g., "Monday"
    val icon: String,         // emoji, e.g., "☀️"
    val condition: String,    // e.g., "Sunny"
    val highTempC: Int,       // high temperature in Celsius
    val lowTempC: Int         // low temperature in Celsius
)