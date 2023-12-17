package com.itinergo.data.service

import com.itinergo.data.request.ForgotPasswordRequest
import com.itinergo.data.request.ItineraryRequest
import com.itinergo.data.request.LoginRequest
import com.itinergo.data.request.RegisterRequest
import com.itinergo.data.response.ForgotPasswordResponse
import com.itinergo.data.response.GetItineraryResponse
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.response.PostAddPlaceResponse
import com.itinergo.data.response.PostItineraryResponse
import com.itinergo.data.response.RegisterResponse
import com.itinergo.data.response.SavedPlaceResponse
import com.itinergo.data.response.VisitedPlaceResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("auth/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @GET("visited-place/get-all")
    fun getAllVisitedPlace(): Call<VisitedPlaceResponse>

    @POST("itinerary/predict")
    fun postItinerary(@Body request: ItineraryRequest): Call<PostItineraryResponse>

    //getall
    @GET("itinerary/get-recomend")
    fun getAllItinerary(): Call<GetItineraryResponse>

    // untuk detail
    @GET("itinerary/get-recomend")
    fun getItineraryByQuery(@Query("place_name") placeName: String): Call<GetItineraryResponse>

    @Multipart
    @POST("visited-place/create")
    fun postAddPlace(
        @Part("city") city: RequestBody,
        @Part("country") country: RequestBody,
        @Part("month") month: RequestBody,
        @Part("year") year: RequestBody,
        @Part image: MultipartBody.Part
    ): Call<PostAddPlaceResponse>

    @GET("detail-itinerary/get-all")
    fun getAllSavedPlace(): Call <SavedPlaceResponse>
}