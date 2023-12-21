package com.itinergo.data.response.budgeting


import com.google.gson.annotations.SerializedName

data class GetAllBudgeting(
    @SerializedName("data")
    val `data`: List<DataBudgeting>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean
)