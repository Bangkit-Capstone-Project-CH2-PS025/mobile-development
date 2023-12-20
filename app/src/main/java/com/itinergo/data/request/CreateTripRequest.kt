package com.itinergo.data.request


import com.google.gson.annotations.SerializedName

data class CreateTripRequest(
    @SerializedName("city")
    val city: String,
    @SerializedName("contact")
    val contact: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("persons")
    val persons: String,
    @SerializedName("until")
    val until: String
)