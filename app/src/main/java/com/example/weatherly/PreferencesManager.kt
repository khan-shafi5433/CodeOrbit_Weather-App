package com.example.weatherly

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("weatherly_prefs", Context.MODE_PRIVATE)

    // Selected city
    fun getSelectedCityId(): Int {
        return prefs.getInt("selected_city_id", 1) // default to Mumbai (id=1)
    }

    fun setSelectedCityId(cityId: Int) {
        prefs.edit().putInt("selected_city_id", cityId).apply()
    }

    // Favorites
    fun getFavoriteCityIds(): Set<Int> {
        val set = prefs.getStringSet("favorite_city_ids", emptySet()) ?: emptySet()
        return set.mapNotNull { it.toIntOrNull() }.toSet()
    }

    fun setFavoriteCityIds(cityIds: Set<Int>) {
        val stringSet = cityIds.map { it.toString() }.toSet()
        prefs.edit().putStringSet("favorite_city_ids", stringSet).apply()
    }

    fun isCityFavorite(cityId: Int): Boolean {
        return getFavoriteCityIds().contains(cityId)
    }

    fun toggleCityFavorite(cityId: Int) {
        val current = getFavoriteCityIds().toMutableSet()
        if (current.contains(cityId)) {
            current.remove(cityId)
        } else {
            current.add(cityId)
        }
        setFavoriteCityIds(current)
    }

    // Dark mode (will be used in Phase 8)
    fun isDarkModeEnabled(): Boolean {
        return prefs.getBoolean("dark_mode_enabled", false)
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("dark_mode_enabled", enabled).apply()
    }

    // Temperature unit: "C" or "F" (will be used in Phase 8)
    fun getTemperatureUnit(): String {
        return prefs.getString("temperature_unit", "C") ?: "C"
    }

    fun setTemperatureUnit(unit: String) {
        prefs.edit().putString("temperature_unit", unit).apply()
    }
}