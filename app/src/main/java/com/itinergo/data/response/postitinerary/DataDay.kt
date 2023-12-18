package com.itinergo.data.response.postitinerary


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataDay(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("place_name")
    val placeName: String?
):Parcelable