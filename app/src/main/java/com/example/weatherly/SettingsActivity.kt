package com.example.weatherly

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchDarkMode: SwitchMaterial
    private lateinit var switchTemperatureUnit: SwitchMaterial
    private lateinit var imageViewBack: ImageView
    private lateinit var prefs: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = PreferencesManager(this)

        switchDarkMode = findViewById(R.id.switchDarkMode)
        switchTemperatureUnit = findViewById(R.id.switchTemperatureUnit)
        imageViewBack = findViewById(R.id.imageViewBack)

        // Load current settings
        switchDarkMode.isChecked = prefs.isDarkModeEnabled()
        // OFF = Celsius, ON = Fahrenheit
        switchTemperatureUnit.isChecked = prefs.getTemperatureUnit() == "F"

        // Back button
        imageViewBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Dark mode change
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            prefs.setDarkModeEnabled(isChecked)
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Temperature unit change
        switchTemperatureUnit.setOnCheckedChangeListener { _, isChecked ->
            val unit = if (isChecked) "F" else "C"
            prefs.setTemperatureUnit(unit)
        }
    }
}