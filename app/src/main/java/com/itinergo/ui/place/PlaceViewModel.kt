package com.itinergo.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.request.LoginRequest
import com.itinergo.data.response.BaseResponse
import com.itinergo.data.response.ErrorResponse
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.response.VisitedPlaceResponse
import com.itinergo.data.service.ApiService
import com.itinergo.utils.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val client: ApiService,
    private val pref: DatastoreManager
) : ViewModel() {

    val visitedPlaceResult: MutableLiveData<BaseResponse<VisitedPlaceResponse>> = MutableLiveData()

    fun getAllVisitedPlace() {
        visitedPlaceResult.value = BaseResponse.Loading()
        client.getAllVisitedPlace()
            .enqueue(object : Callback<VisitedPlaceResponse> {
                override fun onResponse(
                    call: Call<VisitedPlaceResponse>,
                    response: Response<VisitedPlaceResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        visitedPlaceResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            visitedPlaceResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            visitedPlaceResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<VisitedPlaceResponse>, t: Throwable) {
                    visitedPlaceResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
}