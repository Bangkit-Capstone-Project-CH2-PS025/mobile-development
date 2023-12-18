package com.itinergo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.itinergo.data.request.ItineraryRequest
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.getitinerary.GetItineraryResponse
import com.itinergo.data.response.postitinerary.PostItineraryResponse
import com.itinergo.data.service.ApiService
import com.itinergo.utils.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val client: ApiService,
    private val pref: DatastoreManager
) : ViewModel() {

    val itineraryResult: MutableLiveData<BaseResponse<GetItineraryResponse>> = MutableLiveData()
    val postItineraryResult: MutableLiveData<BaseResponse<PostItineraryResponse>> = MutableLiveData()

    fun postItinerary(city: String, budget: Int, duration: Int, preferences1: String, preferences2: String) {
        postItineraryResult.value = BaseResponse.Loading()
        client.postItinerary(ItineraryRequest(city,budget, duration, preferences1, preferences2))
            .enqueue(object : Callback<PostItineraryResponse> {
                override fun onResponse(
                    call: Call<PostItineraryResponse>,
                    response: Response<PostItineraryResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        postItineraryResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            postItineraryResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            postItineraryResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<PostItineraryResponse>, t: Throwable) {
                    postItineraryResult.value = BaseResponse.Error("Network Error")
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
    fun getDataStoreName(): LiveData<String> {
        return pref.getName.asLiveData()
    }

}