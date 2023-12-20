package com.itinergo.data.response.budgeting


import com.google.gson.annotations.SerializedName

data class DataBudgeting(
    @SerializedName("attractions")
    val attractions: Int,
    @SerializedName("budget_name")
    val budgetName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("flight")
    val flight: Int,
    @SerializedName("food")
    val food: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("others")
    val others: Int,
    @SerializedName("shopping")
    val shopping: Int,
    @SerializedName("stay")
    val stay: Int,
    @SerializedName("target")
    val target: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)