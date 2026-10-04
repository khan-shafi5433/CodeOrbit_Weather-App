# Weatherly – Weather App (Kotlin + XML)

A simple Android weather app built with Kotlin and XML as part of an internship task for **CodeOrbit**.

## Features

- Current weather display (temperature, condition, humidity, etc.)
- 5-day forecast view
- List of cities with weather info
- Settings screen (unit selection, theme, etc.)
- Clean Material Design UI with light/dark theme support

## Tech Stack

- Language: Kotlin
- UI: XML layouts + View system
- Architecture: Activity-based
- Dependency management: Gradle (Kotlin DSL)
- Min SDK: 24 (Android 7.0)
- Target SDK: 35 (Android 15)

## Project Structure

- `app/src/main/java/com/example/weatherly/`
  - `MainActivity.kt` – Main screen with current weather
  - `ForecastActivity.kt` – 5-day forecast
  - `CitiesActivity.kt` – List of cities
  - `SettingsActivity.kt` – App settings
  - `City.kt`, `DailyForecast.kt` – Data models
  - `CityAdapter.kt`, `ForecastAdapater.kt` – RecyclerView adapters
  - `PreferencesManager.kt` – SharedPreferences wrapper
  - `SampleWeatherData.kt` – Mock weather data

- `app/src/main/res/layout/`
  - `activity_main.xml`
  - `activity_forecast.xml`
  - `activity_cities.xml`
  - `activity_settings.xml`
  - `item_city.xml`, `item_forecast.xml`, `layout_action_bar.xml`

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/khan-shafi5433/CodeOrbit_Weather-App.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and run on an emulator or device.

## Screenshots

<img width="786" height="1601" alt="image" src="https://github.com/user-attachments/assets/82e2e5e4-bcb1-4dfc-b194-6fa5950fcf1c" />
<img width="786" height="1601" alt="image" src="https://github.com/user-attachments/assets/766e53be-61ec-4ac8-9c15-9c94c568d856" />
<img width="786" height="1601" alt="image" src="https://github.com/user-attachments/assets/cb3477b5-62e4-4f22-922a-650b55dfb5f9" />
<img width="786" height="1601" alt="image" src="https://github.com/user-attachments/assets/313aac92-aef0-414d-b8b0-88f570f9da4b" />


## Credits

- Internship task by **CodeOrbit**
- Developed by: **Shafi Khan** (khan-shafi5433)

## License

This project is for educational and internship purposes.
