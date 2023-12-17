package com.itinergo.data.response


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataX(
    @SerializedName("day-1")
    val day1: List<DataDay>,
    @SerializedName("day-2")
    val day2: List<DataDay>,
    @SerializedName("day-3")
    val day3: List<DataDay>
):Parcelable