package com.template.easypayments.presentation.payments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.easypayments.data.models.Payment
import com.template.easypayments.domain.repository.PaymentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(private val paymentsRepository: PaymentsRepository) :
    ViewModel() {
    private val _listOfPayments = MutableLiveData<List<Payment>>()
    val listOfPayments: LiveData<List<Payment>> get() = _listOfPayments


    fun getAllPayments() {
        viewModelScope.launch {
            val result = paymentsRepository.getPayments(paymentsRepository.getTokenFromSharedPreferences()!!)
            if(result.response != null){
                _listOfPayments.value = result.response
            }
        }
    }
    fun logout(){
        paymentsRepository.deleteTokenFromSharedPreferences()
    }
}