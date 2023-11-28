package com.itinergo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.itinergo.data.request.LoginRequest
import com.itinergo.data.response.BaseResponse
import com.itinergo.data.response.ErrorResponse
import com.itinergo.data.response.LoginResponse
import com.itinergo.data.service.ApiService
import com.itinergo.utils.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val client: ApiService,
    private val pref: DatastoreManager
) : ViewModel() {

    val loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun loginUser(usernameOrEmail: String, password: String) {
        loginResult.value = BaseResponse.Loading()
        client.loginUser(LoginRequest(usernameOrEmail, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        loginResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            loginResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            loginResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    loginResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    fun saveIsLoginStatus(status: Boolean) {
        viewModelScope.launch {
            pref.saveIsLoginStatus(status)
        }
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            pref.saveToken(token)
        }
    }

    fun getDataStoreIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin.asLiveData()
    }

}