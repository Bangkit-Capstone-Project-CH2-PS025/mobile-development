package com.itinergo.ui.account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.itinergo.data.request.UpdateRequest
import com.itinergo.data.response.account.CountByCityResponse
import com.itinergo.data.response.account.CountByCountryResponse
import com.itinergo.data.response.account.GetAccountResponse
import com.itinergo.data.response.account.UpdateProfileResponse
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.service.ApiService
import com.itinergo.utils.DatastoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val client: ApiService,
    private val pref: DatastoreManager
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

    val updateProfileResult: MutableLiveData<BaseResponse<UpdateProfileResponse>> =
        MutableLiveData()

    fun updateAccount(name: RequestBody, email: RequestBody, images: MultipartBody.Part) {
        updateProfileResult.value = BaseResponse.Loading()
        client.updateAccount(name, email, images)
            .enqueue(object : Callback<UpdateProfileResponse> {
                override fun onResponse(
                    call: Call<UpdateProfileResponse>,
                    response: Response<UpdateProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        updateProfileResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            updateProfileResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            updateProfileResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                    updateProfileResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    fun updateAccountWithoutImage(email: String, name: String, images: String) {
        updateProfileResult.value = BaseResponse.Loading()
        client.updateAccountWithoutImage(UpdateRequest(name, email, images))
            .enqueue(object : Callback<UpdateProfileResponse> {
                override fun onResponse(
                    call: Call<UpdateProfileResponse>,
                    response: Response<UpdateProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        updateProfileResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            updateProfileResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            updateProfileResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                    updateProfileResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    val countryResult: MutableLiveData<BaseResponse<CountByCountryResponse>> = MutableLiveData()

    fun getCountByCountry() {
        countryResult.value = BaseResponse.Loading()
        client.getCountByCountry()
            .enqueue(object : Callback<CountByCountryResponse> {
                override fun onResponse(
                    call: Call<CountByCountryResponse>,
                    response: Response<CountByCountryResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        countryResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            countryResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            countryResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CountByCountryResponse>, t: Throwable) {
                    countryResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    val cityResult: MutableLiveData<BaseResponse<CountByCityResponse>> = MutableLiveData()

    fun getCountByCity() {
        cityResult.value = BaseResponse.Loading()
        client.getCountByCity()
            .enqueue(object : Callback<CountByCityResponse> {
                override fun onResponse(
                    call: Call<CountByCityResponse>,
                    response: Response<CountByCityResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        cityResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            cityResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            cityResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CountByCityResponse>, t: Throwable) {
                    cityResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    fun removeName() {
        viewModelScope.launch {
            pref.removeName()
        }
    }
    fun removeToken() {
        viewModelScope.launch {
            pref.removeToken()
        }
    }
    fun removeIsLoginStatus() {
        viewModelScope.launch {
            pref.removeIsLoginStatus()
        }
    }
}