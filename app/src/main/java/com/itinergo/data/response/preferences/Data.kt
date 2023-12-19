package com.itinergo.data.response.preferences


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bus_co2")
    val busCo2: String,
    @SerializedName("bus_price")
    val busPrice: Int,
    @SerializedName("car_co2")
    val carCo2: String,
    @SerializedName("car_price")
    val carPrice: Int,
    @SerializedName("motorbike_co2")
    val motorbikeCo2: String,
    @SerializedName("motorbike_price")
    val motorbikePrice: Int,
    @SerializedName("total_budget")
    val totalBudget: Int,
    @SerializedName("total_days")
    val totalDays: Int,
    @SerializedName("total_distance")
    val totalDistance: Int,
    @SerializedName("total_places")
    val totalPlaces: Int
)