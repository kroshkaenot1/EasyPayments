package com.template.easypayments.data.remote

import com.template.easypayments.data.models.ApiResponse
import com.template.easypayments.data.models.ApiResponsePayments
import com.template.easypayments.domain.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface APIPayments {
    @POST("login")
    suspend fun login(
        @Body credentials: LoginRequest,
        @Header("app-key") appKey: Int = 12345,
        @Header("v") version: Int = 1
    ): ApiResponse

    @GET("payments")
    suspend fun getPayments(
        @Header("token") token: String, @Header("app-key") appKey: Int = 12345,
        @Header("v") version: Int = 1
    ): ApiResponsePayments
}