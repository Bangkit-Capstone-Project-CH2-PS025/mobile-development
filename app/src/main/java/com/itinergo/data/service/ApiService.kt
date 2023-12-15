package com.itinergo.data.service

import com.itinergo.data.request.ForgotPasswordRequest
import com.itinergo.data.request.LoginRequest
import com.itinergo.data.request.RegisterRequest
import com.itinergo.data.response.DataItinerary
import com.itinergo.data.response.ForgotPasswordResponse
import com.itinergo.data.response.GetItineraryResponse
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.response.RegisterResponse
import com.itinergo.data.response.VisitedPlaceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    fun loginUser(@Body loginRequest : LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun registerUser(@Body registerRequest : RegisterRequest): Call<RegisterResponse>

    @POST("auth/forgot-password")
    fun forgotPassword(@Body request : ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @GET("visited-place/get-all")
    fun getAllVisitedPlace(): Call<VisitedPlaceResponse>

    //getall
    @GET("itinerary/get-recomend")
    fun getAllItinerary(): Call<GetItineraryResponse>

    // untuk detail
    @GET("itinerary/get-recomend")
    fun getItineraryByQuery(@Query ("place_name") placeName: String): Call<DataItinerary>
}