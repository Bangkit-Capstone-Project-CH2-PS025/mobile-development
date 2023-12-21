package com.itinergo.data.request


import com.google.gson.annotations.SerializedName

data class UpdateBudgetingRequest(
    @SerializedName("attractions")
    val attractions: String,
    @SerializedName("flight")
    val flight: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("others")
    val others: String,
    @SerializedName("shopping")
    val shopping: String,
    @SerializedName("stay")
    val stay: String
)