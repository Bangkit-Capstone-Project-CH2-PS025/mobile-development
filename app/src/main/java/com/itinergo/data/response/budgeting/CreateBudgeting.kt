package com.itinergo.data.response.budgeting


import com.google.gson.annotations.SerializedName

data class CreateBudgeting(
    @SerializedName("data")
    val `data`: DataBudgeting,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)