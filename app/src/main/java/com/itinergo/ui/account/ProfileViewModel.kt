package com.itinergo.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.response.account.GetAccountResponse
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val client: ApiService
) : ViewModel() {

    val profileResult: MutableLiveData<BaseResponse<GetAccountResponse>> = MutableLiveData()

    fun getAccount() {
        profileResult.value = BaseResponse.Loading()
        client.getAccount()
            .enqueue(object : Callback<GetAccountResponse> {
                override fun onResponse(
                    call: Call<GetAccountResponse>,
                    response: Response<GetAccountResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        profileResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            profileResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            profileResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetAccountResponse>, t: Throwable) {
                    profileResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
}