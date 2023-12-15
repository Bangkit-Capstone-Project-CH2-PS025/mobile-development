package com.itinergo.data.request


import com.google.gson.annotations.SerializedName

data class ItineraryRequest(
    @SerializedName("city")
    val city: String,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("user_preferences_1")
    val preferences1: String,
    @SerializedName("user_preferences_2")
    val preferences2: String
)