package com.template.easypayments.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.easypayments.domain.model.User
import com.template.easypayments.domain.repository.PaymentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val paymentsRepository: PaymentsRepository) :
    ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun isTokenExist(): Boolean {
        return paymentsRepository.getTokenFromSharedPreferences() != null
    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            val result = paymentsRepository.login(login, password)
            if (result.response != null) {
                _user.value = User(login = login, password = password, token = result.response.token)
                paymentsRepository.saveTokenToSharedPreferences(token = result.response.token)
            } else {
                _errorMessage.value = "Ошибка авторизации: неправильный логин или пароль"
            }
        }
    }
}