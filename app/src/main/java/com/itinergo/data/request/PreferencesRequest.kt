package com.itinergo.data.request

import com.google.gson.annotations.SerializedName

data class PreferencesRequest(
    @SerializedName("city")
    val city: String,
)