package com.template.easypayments.data.repositoryimpl

import android.content.SharedPreferences
import com.template.easypayments.data.models.ApiResponse
import com.template.easypayments.data.models.ApiResponsePayments
import com.template.easypayments.data.remote.APIPayments
import com.template.easypayments.domain.model.LoginRequest
import com.template.easypayments.domain.repository.PaymentsRepository
import com.template.easypayments.utils.Constants
import javax.inject.Inject

class PaymentsRepositoryImpl @Inject constructor(
    private val apiPayments: APIPayments,
    private val sharedPreferences: SharedPreferences
) :
    PaymentsRepository {

    override suspend fun login(login: String, password: String): ApiResponse {
        val credentials = LoginRequest(login, password)
        return apiPayments.login(credentials)
    }

    override suspend fun getPayments(token: String): ApiResponsePayments {
        return apiPayments.getPayments(token)
    }

    override fun getTokenFromSharedPreferences(): String? =
        sharedPreferences.getString(Constants.tokenSharedPreferencesKey, null)

    override fun saveTokenToSharedPreferences(token: String) {
        sharedPreferences.edit().putString(Constants.tokenSharedPreferencesKey,token).apply()
    }

    override fun deleteTokenFromSharedPreferences() {
        sharedPreferences.edit().remove(Constants.tokenSharedPreferencesKey).apply()
    }
}