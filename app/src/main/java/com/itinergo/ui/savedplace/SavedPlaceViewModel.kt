package com.itinergo.ui.savedplace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.finishsaved.FinishSavedResponse
import com.itinergo.data.response.savedplace.DetailSavedPlaceResponse
import com.itinergo.data.response.savedplace.SavedPlaceResponse
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SavedPlaceViewModel @Inject constructor(
    private val client: ApiService
) : ViewModel() {

    val savedPlaceResult: MutableLiveData<BaseResponse<SavedPlaceResponse>> = MutableLiveData()
    val detailSavedPlaceResult: MutableLiveData<BaseResponse<DetailSavedPlaceResponse>> = MutableLiveData()
    val updateSavedPlaceResult: MutableLiveData<BaseResponse<FinishSavedResponse>> = MutableLiveData()

    fun getAllSavedPlace() {
        savedPlaceResult.value = BaseResponse.Loading()
        client.getAllSavedPlace()
            .enqueue(object : Callback<SavedPlaceResponse> {
                override fun onResponse(
                    call: Call<SavedPlaceResponse>,
                    response: Response<SavedPlaceResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        savedPlaceResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            savedPlaceResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            savedPlaceResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<SavedPlaceResponse>, t: Throwable) {
                    savedPlaceResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    fun getDetailSavedPlace(id: String) {
        detailSavedPlaceResult.value = BaseResponse.Loading()
        client.getDetailSavedPlace(id)
            .enqueue(object : Callback<DetailSavedPlaceResponse> {
                override fun onResponse(
                    call: Call<DetailSavedPlaceResponse>,
                    response: Response<DetailSavedPlaceResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        detailSavedPlaceResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            detailSavedPlaceResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            detailSavedPlaceResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<DetailSavedPlaceResponse>, t: Throwable) {
                    detailSavedPlaceResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    fun updateFinishSaved(id: String) {
        updateSavedPlaceResult.value = BaseResponse.Loading()
        client.updateFinishSaved(id)
            .enqueue(object : Callback<FinishSavedResponse> {
                override fun onResponse(
                    call: Call<FinishSavedResponse>,
                    response: Response<FinishSavedResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        updateSavedPlaceResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            updateSavedPlaceResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            updateSavedPlaceResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<FinishSavedResponse>, t: Throwable) {
                    updateSavedPlaceResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

}