package com.itinergo.ui.travelbudgeting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.itinergo.data.request.BudgetingRequest
import com.itinergo.data.response.base.BaseResponse
import com.itinergo.data.response.base.ErrorResponse
import com.itinergo.data.response.budgeting.CreateBudgeting
import com.itinergo.data.response.budgeting.GetAllBudgeting
import com.itinergo.data.service.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TravelBudgetingViewModel @Inject constructor(
    private val client: ApiService
) : ViewModel() {

    val allBudgetingResult: MutableLiveData<BaseResponse<GetAllBudgeting>> = MutableLiveData()

    fun getAllBudgeting() {
        allBudgetingResult.value = BaseResponse.Loading()
        client.getAllTravelBudget()
            .enqueue(object : Callback<GetAllBudgeting> {
                override fun onResponse(
                    call: Call<GetAllBudgeting>,
                    response: Response<GetAllBudgeting>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        allBudgetingResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            allBudgetingResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            allBudgetingResult.value = BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<GetAllBudgeting>, t: Throwable) {
                    allBudgetingResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    val createBudgetingResult: MutableLiveData<BaseResponse<CreateBudgeting>> = MutableLiveData()

    fun createBudgeting(
        budgetName: String,
        target: String,
        flight: String,
        attractions: String,
        shopping: String,
        food: String,
        stay: String,
        others: String
    ) {
        createBudgetingResult.value = BaseResponse.Loading()
        client.createBudgeting(
            BudgetingRequest(
                attractions,
                budgetName,
                flight,
                food,
                others,
                shopping,
                stay,
                target
            )
        )
            .enqueue(object : Callback<CreateBudgeting> {
                override fun onResponse(
                    call: Call<CreateBudgeting>,
                    response: Response<CreateBudgeting>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        createBudgetingResult.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            createBudgetingResult.value = BaseResponse.Error(errorMessage)
                        } else {
                            createBudgetingResult.value =
                                BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CreateBudgeting>, t: Throwable) {
                    createBudgetingResult.value = BaseResponse.Error("Network Error")
                }
            })
    }

    val travelBudgetById: MutableLiveData<BaseResponse<CreateBudgeting>> = MutableLiveData()

    fun getTravelBudgetById(
        id: String
    ) {
        travelBudgetById.value = BaseResponse.Loading()
        client.getTravelBudgetById(id)
            .enqueue(object : Callback<CreateBudgeting> {
                override fun onResponse(
                    call: Call<CreateBudgeting>,
                    response: Response<CreateBudgeting>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        travelBudgetById.value = BaseResponse.Success(responseBody)
                    } else {
                        val errorBody = response.errorBody()
                        if (errorBody != null) {
                            val errorResponse =
                                Gson().fromJson(errorBody.charStream(), ErrorResponse::class.java)
                            val errorMessage = errorResponse.message
                            travelBudgetById.value = BaseResponse.Error(errorMessage)
                        } else {
                            travelBudgetById.value =
                                BaseResponse.Error("Unknown error occurred")
                        }
                    }
                }

                override fun onFailure(call: Call<CreateBudgeting>, t: Throwable) {
                    travelBudgetById.value = BaseResponse.Error("Network Error")
                }
            })
    }
}