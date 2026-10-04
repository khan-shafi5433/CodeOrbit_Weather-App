package com.example.weatherly

object SampleWeatherData {

    val cities: List<City> = listOf(
        City(
            id = 1,
            name = "Mumbai",
            country = "IN",
            currentTempC = 29,
            feelsLikeTempC = 31,
            condition = "Partly Cloudy",
            icon = "⛅",
            humidityPercent = 72,
            windSpeedKmh = 14,
            sunriseTime = "6:25 AM",
            sunsetTime = "6:18 PM",
            summary = "Warm and humid throughout the day with partly cloudy skies.",
            forecast = listOf(
                DailyForecast("Monday", "⛅", "Partly Cloudy", 31, 25),
                DailyForecast("Tuesday", "🌤️", "Partly Cloudy", 30, 25),
                DailyForecast("Wednesday", "🌧️", "Rain", 28, 24),
                DailyForecast("Thursday", "⛅", "Partly Cloudy", 29, 25),
                DailyForecast("Friday", "☀️", "Sunny", 32, 26)
            )
        ),

        City(
            id = 2,
            name = "Delhi",
            country = "IN",
            currentTempC = 33,
            feelsLikeTempC = 35,
            condition = "Sunny",
            icon = "☀️",
            humidityPercent = 45,
            windSpeedKmh = 10,
            sunriseTime = "6:10 AM",
            sunsetTime = "6:40 PM",
            summary = "Hot and dry with clear skies throughout the day.",
            forecast = listOf(
                DailyForecast("Monday", "☀️", "Sunny", 35, 26),
                DailyForecast("Tuesday", "☀️", "Sunny", 36, 27),
                DailyForecast("Wednesday", "☀️", "Sunny", 34, 26),
                DailyForecast("Thursday", "⛅", "Partly Cloudy", 33, 25),
                DailyForecast("Friday", "☀️", "Sunny", 35, 27)
            )
        ),

        City(
            id = 3,
            name = "Bengaluru",
            country = "IN",
            currentTempC = 27,
            feelsLikeTempC = 28,
            condition = "Cloudy",
            icon = "☁️",
            humidityPercent = 65,
            windSpeedKmh = 12,
            sunriseTime = "6:15 AM",
            sunsetTime = "6:30 PM",
            summary = "Pleasant and cloudy with mild temperatures.",
            forecast = listOf(
                DailyForecast("Monday", "☁️", "Cloudy", 28, 21),
                DailyForecast("Tuesday", "🌤️", "Partly Cloudy", 29, 22),
                DailyForecast("Wednesday", "🌧️", "Rain", 26, 20),
                DailyForecast("Thursday", "☁️", "Cloudy", 27, 21),
                DailyForecast("Friday", "🌤️", "Partly Cloudy", 28, 22)
            )
        ),

        City(
            id = 4,
            name = "London",
            country = "UK",
            currentTempC = 15,
            feelsLikeTempC = 14,
            condition = "Cloudy",
            icon = "☁️",
            humidityPercent = 78,
            windSpeedKmh = 18,
            sunriseTime = "6:45 AM",
            sunsetTime = "7:05 PM",
            summary = "Cool and overcast with a chance of light showers.",
            forecast = listOf(
                DailyForecast("Monday", "☁️", "Cloudy", 16, 11),
                DailyForecast("Tuesday", "🌦️", "Light Rain", 15, 10),
                DailyForecast("Wednesday", "🌤️", "Partly Cloudy", 17, 11),
                DailyForecast("Thursday", "☁️", "Cloudy", 16, 10),
                DailyForecast("Friday", "🌧️", "Rain", 14, 9)
            )
        ),

        City(
            id = 5,
            name = "Dubai",
            country = "AE",
            currentTempC = 38,
            feelsLikeTempC = 42,
            condition = "Sunny",
            icon = "☀️",
            humidityPercent = 55,
            windSpeedKmh = 16,
            sunriseTime = "5:55 AM",
            sunsetTime = "6:50 PM",
            summary = "Very hot and sunny with clear skies.",
            forecast = listOf(
                DailyForecast("Monday", "☀️", "Sunny", 39, 30),
                DailyForecast("Tuesday", "☀️", "Sunny", 40, 31),
                DailyForecast("Wednesday", "☀️", "Sunny", 38, 30),
                DailyForecast("Thursday", "☀️", "Sunny", 39, 31),
                DailyForecast("Friday", "☀️", "Sunny", 41, 32)
            )
        )
    )

    fun getCityById(id: Int): City? {
        return cities.find { it.id == id }
    }
}