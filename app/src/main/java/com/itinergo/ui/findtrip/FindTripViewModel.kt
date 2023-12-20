package com.itinergo.ui.findtrip

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.findtrip.CreateTripResponse
import com.itinergo.data.response.findtrip.GetAllTripByIdResponse
import com.itinergo.data.response.findtrip.GetAllTripResponse
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
class FindTripViewModel @Inject constructor(
    private val client: ApiService, private val pref: DatastoreManager
) : ViewModel() {

    val allTripResult: MutableLiveData<BaseResponse<GetAllTripResponse>> = MutableLiveData()

    fun getAllTrip() {
        allTripResult.value = BaseResponse.Loading()
        client.getAllFindTrip().enqueue(object : Callback<GetAllTripResponse> {
            override fun onResponse(
                call: Call<GetAllTripResponse>,
                response: Response<GetAllTripResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    allTripResult.value = BaseResponse.Success(responseBody)
                } else {
                    val errorBody = response.errorBody()
                    if (errorBody != null) {
                        val errorResponse =
                            Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                        val errorMessage = errorResponse.message
                        allTripResult.value = BaseResponse.Error(errorMessage)
                    } else {
                        allTripResult.value = BaseResponse.Error("Unknown error occurred")
                    }
                }
            }

            override fun onFailure(call: Call<GetAllTripResponse>, t: Throwable) {
                allTripResult.value = BaseResponse.Error("Network Error")
            }
        })
    }

    val allTripByIdResult: MutableLiveData<BaseResponse<GetAllTripByIdResponse>> = MutableLiveData()

    fun getAllTripById() {
        allTripByIdResult.value = BaseResponse.Loading()
        client.getAllTripById().enqueue(object : Callback<GetAllTripByIdResponse> {
            override fun onResponse(
                call: Call<GetAllTripByIdResponse>,
                response: Response<GetAllTripByIdResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    allTripByIdResult.value = BaseResponse.Success(responseBody)
                } else {
                    val errorBody = response.errorBody()
                    if (errorBody != null) {
                        val errorResponse =
                            Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                        val errorMessage = errorResponse.message
                        allTripByIdResult.value = BaseResponse.Error(errorMessage)
                    } else {
                        allTripByIdResult.value = BaseResponse.Error("Unknown error occurred")
                    }
                }
            }

            override fun onFailure(call: Call<GetAllTripByIdResponse>, t: Throwable) {
                allTripByIdResult.value = BaseResponse.Error("Network Error")
            }
        })
    }

    val createTripResult: MutableLiveData<BaseResponse<CreateTripResponse>> =
        MutableLiveData()

    fun createTrip(
        city: RequestBody,
        country: RequestBody,
        departure: RequestBody,
        until: RequestBody,
        persons: RequestBody,
        contact: RequestBody,
        image: MultipartBody.Part
    ) {
        createTripResult.value = BaseResponse.Loading()
        client.createTrip(city,country,departure,until,persons,contact,image)
            .enqueue(object : Callback<CreateTripResponse> {
                override fun onResponse(
                    call: Call<CreateTripResponse>,
                    response: Response<CreateTripResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        createTripResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            createTripResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            createTripResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CreateTripResponse>, t: Throwable) {
                    createTripResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
//
//    fun updateAccountWithoutImage(email: String, name: String, images: String) {
//        updateProfileResult.value = BaseResponse.Loading()
//        client.updateAccountWithoutImage(UpdateRequest(name, email, images))
//            .enqueue(object : Callback<UpdateProfileResponse> {
//                override fun onResponse(
//                    call: Call<UpdateProfileResponse>,
//                    response: Response<UpdateProfileResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        updateProfileResult.value = BaseResponse.Success(responseBody)
//                    } else {
//                        val errorBody = response.errorBody()
//                        if (errorBody != null) {
//                            val errorResponse =
//                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
//                            val errorMessage = errorResponse.message
//                            updateProfileResult.value = BaseResponse.Error(errorMessage)
//                        } else {
//                            updateProfileResult.value = BaseResponse.Error("Unknown error occurred")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
//                    updateProfileResult.value = BaseResponse.Error("Network Error")
//                }
//            })
//    }
//    val cityResult: MutableLiveData<BaseResponse<CountByCityResponse>> = MutableLiveData()
//
//    fun getCountByCity() {
//        cityResult.value = BaseResponse.Loading()
//        client.getCountByCity()
//            .enqueue(object : Callback<CountByCityResponse> {
//                override fun onResponse(
//                    call: Call<CountByCityResponse>,
//                    response: Response<CountByCityResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val responseBody = response.body()
//                        cityResult.value = BaseResponse.Success(responseBody)
//                    } else {
//                        val errorBody = response.errorBody()
//                        if (errorBody != null) {
//                            val errorResponse =
//                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
//                            val errorMessage = errorResponse.message
//                            cityResult.value = BaseResponse.Error(errorMessage)
//                        } else {
//                            cityResult.value = BaseResponse.Error("Unknown error occurred")
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<CountByCityResponse>, t: Throwable) {
//                    cityResult.value = BaseResponse.Error("Network Error")
//                }
//            })
//    }
}