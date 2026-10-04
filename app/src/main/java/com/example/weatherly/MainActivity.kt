package com.example.weatherly

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    private lateinit var textViewCity: TextView
    private lateinit var textViewWeatherIcon: TextView
    private lateinit var textViewTemperature: TextView
    private lateinit var textViewCondition: TextView
    private lateinit var textViewWeatherStatus: TextView
    private lateinit var textViewFeelsLike: TextView
    private lateinit var textViewHumidity: TextView
    private lateinit var textViewWind: TextView
    private lateinit var textViewSunrise: TextView
    private lateinit var textViewSunset: TextView
    private lateinit var textViewSummary: TextView
    private lateinit var buttonOpenCities: MaterialButton
    private lateinit var buttonOpenForecast: MaterialButton
    private lateinit var imageViewSettings: ImageView
    private lateinit var switchTemperatureUnit: SwitchMaterial
    private lateinit var rootLayout: LinearLayout
    private lateinit var scrollViewRoot: ScrollView

    private lateinit var prefs: PreferencesManager
    private var selectedCityId: Int = 1

    private val citiesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val id = result.data?.getIntExtra("SELECTED_CITY_ID", 1) ?: 1
            selectedCityId = id
            prefs.setSelectedCityId(id)
            updateWeatherForSelectedCity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme before setting content
        prefs = PreferencesManager(this)
        val isDark = prefs.isDarkModeEnabled()
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedCityId = prefs.getSelectedCityId()

        // Initialize views
        textViewCity = findViewById(R.id.textViewCity)
        textViewWeatherIcon = findViewById(R.id.textViewWeatherIcon)
        textViewTemperature = findViewById(R.id.textViewTemperature)
        textViewCondition = findViewById(R.id.textViewCondition)
        textViewWeatherStatus = findViewById(R.id.textViewWeatherStatus)
        textViewFeelsLike = findViewById(R.id.textViewFeelsLike)
        textViewHumidity = findViewById(R.id.textViewHumidity)
        textViewWind = findViewById(R.id.textViewWind)
        textViewSunrise = findViewById(R.id.textViewSunrise)
        textViewSunset = findViewById(R.id.textViewSunset)
        textViewSummary = findViewById(R.id.textViewSummary)
        buttonOpenCities = findViewById(R.id.buttonOpenCities)
        buttonOpenForecast = findViewById(R.id.buttonOpenForecast)
        imageViewSettings = findViewById(R.id.imageViewSettings)
        switchTemperatureUnit = findViewById(R.id.switchTemperatureUnit)
        rootLayout = findViewById(R.id.rootLayout)
        scrollViewRoot = findViewById(R.id.scrollViewRoot)

        // Settings icon click
        imageViewSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        buttonOpenCities.setOnClickListener {
            val intent = Intent(this, CitiesActivity::class.java)
            citiesLauncher.launch(intent)
        }

        buttonOpenForecast.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putExtra("CITY_ID", selectedCityId)
            startActivity(intent)
        }

        // Initialize C/F switch from saved preference
        switchTemperatureUnit.isChecked = prefs.getTemperatureUnit() == "F"

        // C/F toggle change
        switchTemperatureUnit.setOnCheckedChangeListener { _, isChecked ->
            val unit = if (isChecked) "F" else "C"
            prefs.setTemperatureUnit(unit)
            updateWeatherForSelectedCity()
        }

        updateWeatherForSelectedCity()
    }

    override fun onResume() {
        super.onResume()
        // In case dark mode changed in Settings
        val isDark = prefs.isDarkModeEnabled()
        AppCompatDelegate.setDefaultNightMode(
            if (isDark) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        updateWeatherForSelectedCity()
    }

    private fun updateWeatherForSelectedCity() {
        val city = SampleWeatherData.getCityById(selectedCityId) ?: return

        val unit = prefs.getTemperatureUnit() // "C" or "F"

        // Keep switch in sync if changed elsewhere
        switchTemperatureUnit.isChecked = (unit == "F")

        textViewCity.text = city.name
        textViewWeatherIcon.text = city.icon
        textViewTemperature.text = formatTemperature(city.currentTempC, unit)
        textViewCondition.text = city.condition
        textViewWeatherStatus.text = getWeatherStatusWithIcon(city.condition)
        textViewFeelsLike.text = formatTemperature(city.feelsLikeTempC, unit)
        textViewHumidity.text = "${city.humidityPercent}%"
        textViewWind.text = "${city.windSpeedKmh} km/h"
        textViewSunrise.text = city.sunriseTime
        textViewSunset.text = city.sunsetTime
        textViewSummary.text = city.summary

        applyWeatherBackground(city.condition)
    }

    private fun celsiusToFahrenheit(celsius: Int): Int {
        return (celsius * 9 / 5) + 32
    }

    private fun formatTemperature(celsius: Int, unit: String): String {
        return if (unit == "F") {
            "${celsiusToFahrenheit(celsius)}°F"
        } else {
            "${celsius}°C"
        }
    }

    private fun getWeatherStatusWithIcon(condition: String): String {
        return when {
            condition.contains("Sunny", ignoreCase = true) -> "☀️ Sunny"
            condition.contains("Snow", ignoreCase = true) -> "🌨️ Snowy"
            condition.contains("Rain", ignoreCase = true) -> "🌧️ Rainy"
            condition.contains("Partly Cloudy", ignoreCase = true) -> "⛅ Partly Cloudy"
            condition.contains("Cloudy", ignoreCase = true) -> "☁️ Cloudy"
            else -> "⛅ $condition"
        }
    }

    private fun applyWeatherBackground(condition: String) {
        val colorRes = when {
            condition.contains("Sunny", ignoreCase = true) -> R.color.bg_sunny
            condition.contains("Cloudy", ignoreCase = true) ||
                    condition.contains("Overcast", ignoreCase = true) -> R.color.bg_cloudy
            condition.contains("Rain", ignoreCase = true) -> R.color.bg_rainy
            condition.contains("Partly Cloudy", ignoreCase = true) -> R.color.bg_partly_cloudy
            else -> R.color.bg_default
        }
        val color = getColor(colorRes)
        findViewById<View>(android.R.id.content).background = ColorDrawable(color)
    }
}