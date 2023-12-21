package com.itinergo.data.response.budgeting


import com.google.gson.annotations.SerializedName

data class UpdateBudgetinResponse(
    @SerializedName("data")
    val `data`: List<Int>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)