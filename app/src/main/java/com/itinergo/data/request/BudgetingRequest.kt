package com.itinergo.data.request


import com.google.gson.annotations.SerializedName

data class BudgetingRequest(
    @SerializedName("attractions")
    val attractions: String,
    @SerializedName("budget_name")
    val budgetName: String,
    @SerializedName("flight")
    val flight: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("others")
    val others: String,
    @SerializedName("shopping")
    val shopping: String,
    @SerializedName("stay")
    val stay: String,
    @SerializedName("target")
    val target: String
)