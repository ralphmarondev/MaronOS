package com.ralphmarondev.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.auth.domain.usecase.LoginUseCase
import com.ralphmarondev.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _response = MutableStateFlow<Result?>(null)
    val response = _response.asStateFlow()


    fun onUsernameValueChange(value: String) {
        _username.value = value
    }

    fun onPasswordValueChange(value: String) {
        _password.value = value
    }

    fun login() {
        viewModelScope.launch {
            val response = loginUseCase(
                username = _username.value.trim(),
                password = _password.value.trim()
            )
            _response.value = response
        }
    }

    fun resetResponse() {
        viewModelScope.launch {
            delay(3000)
            _response.value = null
        }
    }
}