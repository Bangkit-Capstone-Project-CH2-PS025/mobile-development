package com.itinergo.data.response


import com.google.gson.annotations.SerializedName

data class DataItinerary(
    @SerializedName("category")
    val category: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinate")
    val coordinate: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>,
    @SerializedName("description")
    val description: String,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("long")
    val long: Double,
    @SerializedName("metadata")
    val metadata: String,
    @SerializedName("metadata_tokenized")
    val metadataTokenized: String,
    @SerializedName("place_name")
    val placeName: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("province")
    val province: String,
    @SerializedName("rating")
    val rating: Double
)