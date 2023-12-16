package com.itinergo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.request.ItineraryRequest
import com.itinergo.data.response.BaseResponse
import com.itinergo.data.response.DataItinerary
import com.itinergo.data.response.ErrorResponse
import com.itinergo.data.response.GetItineraryResponse
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val client: ApiService,
) : ViewModel() {

    val itineraryResult: MutableLiveData<BaseResponse<GetItineraryResponse>> = MutableLiveData()

    fun postItinerary(city: String, budget: Int, duration: Int, preferences1: String, preferences2: String) {
        itineraryResult.value = BaseResponse.Loading()
        client.postItinerary(ItineraryRequest(city,budget, duration, preferences1, preferences2))
            .enqueue(object : Callback<GetItineraryResponse> {
                override fun onResponse(
                    call: Call<GetItineraryResponse>,
                    response: Response<GetItineraryResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        itineraryResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            itineraryResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            itineraryResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetItineraryResponse>, t: Throwable) {
                    itineraryResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    fun getAllItinerary() {
        itineraryResult.value = BaseResponse.Loading()
        client.getAllItinerary()
            .enqueue(object : Callback<GetItineraryResponse> {
                override fun onResponse(
                    call: Call<GetItineraryResponse>,
                    response: Response<GetItineraryResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        itineraryResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            itineraryResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            itineraryResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetItineraryResponse>, t: Throwable) {
                    itineraryResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    val itineraryDetailResult: MutableLiveData<BaseResponse<GetItineraryResponse>> = MutableLiveData()
    fun getItineraryByQuery(placeName: String) {
        itineraryDetailResult.value = BaseResponse.Loading()
        client.getItineraryByQuery(placeName)
            .enqueue(object : Callback<GetItineraryResponse> {
                override fun onResponse(
                    call: Call<GetItineraryResponse>,
                    response: Response<GetItineraryResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        itineraryDetailResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            itineraryDetailResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            itineraryDetailResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetItineraryResponse>, t: Throwable) {
                    itineraryDetailResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

}