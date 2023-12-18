package com.itinergo.data.response.savedplace


import com.google.gson.annotations.SerializedName

data class DataSavedPlace(
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("city")
    val city: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("detail_plans")
    val detailPlans: List<DetailPlan>,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_finished")
    val isFinished: Boolean,
    @SerializedName("preference_1")
    val preference1: String,
    @SerializedName("preference_2")
    val preference2: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)