package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class DataPlace(
    @SerializedName("city")
    val city: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("month")
    val month: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("year")
    val year: Int
)