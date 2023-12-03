package com.itinergo.data.service

import com.itinergo.data.request.LoginRequest
import com.itinergo.data.request.RegisterRequest
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    fun loginUser(@Body loginRequest : LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun registerUser(@Body registerRequest : RegisterRequest): Call<RegisterResponse>
}