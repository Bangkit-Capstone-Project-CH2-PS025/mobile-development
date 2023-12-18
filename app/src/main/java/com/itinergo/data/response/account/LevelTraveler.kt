package com.itinergo.data.response.account


import com.google.gson.annotations.SerializedName

data class LevelTraveler(
    @SerializedName("badge")
    val badge: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)