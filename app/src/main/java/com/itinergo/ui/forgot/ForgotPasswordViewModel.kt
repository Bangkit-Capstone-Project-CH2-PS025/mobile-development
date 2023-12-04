package com.itinergo.ui.forgot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.request.ForgotPasswordRequest
import com.itinergo.data.response.BaseResponse
import com.itinergo.data.response.ErrorResponse
import com.itinergo.data.response.ForgotPasswordResponse
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val client: ApiService,
) : ViewModel() {

    val forgotResult: MutableLiveData<BaseResponse<ForgotPasswordResponse>> = MutableLiveData()

    fun forgotPassword(email: String) {
        forgotResult.value = BaseResponse.Loading()
        client.forgotPassword(ForgotPasswordRequest(email))
            .enqueue(object : Callback<ForgotPasswordResponse> {
                override fun onResponse(
                    call: Call<ForgotPasswordResponse>,
                    response: Response<ForgotPasswordResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        forgotResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            forgotResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            forgotResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                    forgotResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
}