package com.itinergo.ui.traveltips

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.savedplace.SavedPlaceResponse
import com.itinergo.data.response.traveltips.GetAllTravelTipsResponse
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TravelTipsViewModel @Inject constructor(
    private val client: ApiService
): ViewModel() {

    val travelTipsResult: MutableLiveData<BaseResponse<GetAllTravelTipsResponse>> = MutableLiveData()
    fun getAllTravelTips() {
        travelTipsResult.value = BaseResponse.Loading()
        client.getAllTravelTips()
            .enqueue(object : Callback<GetAllTravelTipsResponse> {
                override fun onResponse(
                    call: Call<GetAllTravelTipsResponse>,
                    response: Response<GetAllTravelTipsResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        travelTipsResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            travelTipsResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            travelTipsResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetAllTravelTipsResponse>, t: Throwable) {
                    travelTipsResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
}