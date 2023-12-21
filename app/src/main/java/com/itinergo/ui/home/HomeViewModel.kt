package com.itinergo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.itinergo.data.request.ItineraryRequest
import com.itinergo.data.request.PreferencesRequest
import com.itinergo.data.response.account.GetAccountResponse
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.discover.DiscoverPlaceResponse
import com.itinergo.data.response.getitinerary.GetItineraryResponse
import com.itinergo.data.response.postitinerary.PostItineraryResponse
import com.itinergo.data.response.preferences.CarbonResponse
import com.itinergo.data.response.preferences.PostPreferencesResponse
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

    val postPreferencesResult: MutableLiveData<BaseResponse<PostPreferencesResponse>> = MutableLiveData()
    fun postPreferences(city: String) {
        postPreferencesResult.value = BaseResponse.Loading()
        client.postPreferences(PreferencesRequest(city))
            .enqueue(object : Callback<PostPreferencesResponse> {
                override fun onResponse(
                    call: Call<PostPreferencesResponse>,
                    response: Response<PostPreferencesResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        postPreferencesResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            postPreferencesResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            postPreferencesResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<PostPreferencesResponse>, t: Throwable) {
                    postPreferencesResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
    val getCarbonResult: MutableLiveData<BaseResponse<CarbonResponse>> = MutableLiveData()
    fun getCarbon() {
        getCarbonResult.value = BaseResponse.Loading()
        client.getCarbon()
            .enqueue(object : Callback<CarbonResponse> {
                override fun onResponse(
                    call: Call<CarbonResponse>,
                    response: Response<CarbonResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        getCarbonResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            getCarbonResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            getCarbonResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CarbonResponse>, t: Throwable) {
                    getCarbonResult.value = BaseResponse.Error("Network Error")
                }
            })
    }
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
    val discoverResult: MutableLiveData<BaseResponse<DiscoverPlaceResponse>> = MutableLiveData()

    fun getDiscoverPlace() {
        discoverResult.value = BaseResponse.Loading()
        client.getDiscover()
            .enqueue(object : Callback<DiscoverPlaceResponse> {
                override fun onResponse(
                    call: Call<DiscoverPlaceResponse>,
                    response: Response<DiscoverPlaceResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        discoverResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            discoverResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            discoverResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<DiscoverPlaceResponse>, t: Throwable) {
                    discoverResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    fun getDataStoreName(): LiveData<String> {
        return pref.getName.asLiveData()
    }
    fun getDataStoreId(): LiveData<Int> {
        return pref.getId.asLiveData()
    }

    fun getDataStoreIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin.asLiveData()
    }

}