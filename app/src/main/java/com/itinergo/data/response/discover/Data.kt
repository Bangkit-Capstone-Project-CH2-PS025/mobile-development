package com.itinergo.data.response.discover


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city")
    val city: String,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("place_name")
    val placeName: String
)