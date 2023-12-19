package com.itinergo.data.response.traveltips


import com.google.gson.annotations.SerializedName

data class DataTravelTips(
    @SerializedName("author")
    val author: String,
    @SerializedName("contents")
    val contents: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image_1")
    val image1: String,
    @SerializedName("image_2")
    val image2: String,
    @SerializedName("image_3")
    val image3: String,
    @SerializedName("image_4")
    val image4: String,
    @SerializedName("image_5")
    val image5: String,
    @SerializedName("image_cover")
    val imageCover: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_views")
    val totalViews: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)