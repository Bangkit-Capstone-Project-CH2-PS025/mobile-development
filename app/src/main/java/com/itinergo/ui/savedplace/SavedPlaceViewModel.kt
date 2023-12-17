package com.itinergo.ui.savedplace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.request.LoginRequest
import com.itinergo.data.response.BaseResponse
import com.itinergo.data.response.ErrorResponse
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.response.PostAddPlaceResponse
import com.itinergo.data.response.SavedPlaceResponse
import com.itinergo.data.response.VisitedPlaceResponse
import com.itinergo.data.service.ApiService
import com.itinergo.utils.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SavedPlaceViewModel @Inject constructor(
    private val client: ApiService,
    private val pref: DatastoreManager
) : ViewModel() {

    val savedPlaceResult: MutableLiveData<BaseResponse<SavedPlaceResponse>> = MutableLiveData()

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

}