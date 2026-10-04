package com.example.weatherly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForecastAdapter(
    private val forecastList: List<DailyForecast>
) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDay: TextView = itemView.findViewById(R.id.textViewDay)
        val textViewIcon: TextView = itemView.findViewById(R.id.textViewIcon)
        val textViewCondition: TextView = itemView.findViewById(R.id.textViewCondition)
        val textViewHighLow: TextView = itemView.findViewById(R.id.textViewHighLow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = forecastList[position]

        holder.textViewDay.text = forecast.day
        holder.textViewIcon.text = forecast.icon
        holder.textViewCondition.text = forecast.condition
        holder.textViewHighLow.text = "${forecast.highTempC}° / ${forecast.lowTempC}°"
    }

    override fun getItemCount(): Int = forecastList.size
}