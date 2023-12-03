package com.itinergo.data


import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    suspend fun register(
        @Body() request: RequestBody,
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body() request: RequestBody,
    ): LoginResponse

}

