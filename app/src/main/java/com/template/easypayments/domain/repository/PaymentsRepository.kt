package com.template.easypayments.domain.repository

import com.template.easypayments.data.models.ApiResponse
import com.template.easypayments.data.models.ApiResponsePayments

interface PaymentsRepository {
    suspend fun login(login: String, password: String): ApiResponse
    suspend fun getPayments(token: String): ApiResponsePayments
    fun getTokenFromSharedPreferences(): String?
    fun saveTokenToSharedPreferences(token: String)
    fun deleteTokenFromSharedPreferences()
}