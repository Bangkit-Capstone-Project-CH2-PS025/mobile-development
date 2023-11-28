package com.itinergo.data.service

import com.itinergo.data.request.LoginRequest
import com.itinergo.data.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun loginUser(@Body loginRequest : LoginRequest): Call<LoginResponse>
}