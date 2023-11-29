package com.template.easypayments.data.models

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String?,
    @SerializedName("amount") val amount: String?,
    @SerializedName("created") val created: String?
)

data class ApiResponsePayments(
    @SerializedName("success") val success: String,
    @SerializedName("response") val response: List<Payment>?,
    @SerializedName("error") val error: ApiError?
)
