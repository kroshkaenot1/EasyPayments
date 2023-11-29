package com.template.easypayments.data.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("response") val response: LoginResponse?,
    @SerializedName("error") val error: ApiError?
)

data class LoginResponse(
    @SerializedName("token") val token: String
)

data class ApiError(
    @SerializedName("error_code") val errorCode: Int,
    @SerializedName("error_msg") val errorMsg: String
)
