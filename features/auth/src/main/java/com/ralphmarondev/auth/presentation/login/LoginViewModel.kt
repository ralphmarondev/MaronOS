package com.ralphmarondev.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.data.local.preferences.AppPreferences
import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.domain.model.User
import com.ralphmarondev.domain.usecase.user.LoginUseCase
import com.ralphmarondev.domain.usecase.user.RegisterUseUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseUseCase,
    private val preferences: AppPreferences
) : ViewModel() {

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _response = MutableStateFlow<Result?>(null)
    val response = _response.asStateFlow()


    init {
        viewModelScope.launch {
            val isRootUserExists = preferences.isRootUserExists.first()
            if (!isRootUserExists) {
                val result = registerUseCase(
                    user = User(
                        fullName = "MaronOS User",
                        username = "maron",
                        password = "os"
                    )
                )
                preferences.setupRootUser(value = true)
                preferences.setUserFullName("MaronOS User")
                preferences.setUserFullName("maron")
                preferences.setIsFirstLaunch(value = false)
                println("Creating default user: ${result.message}")
            }
        }
    }

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