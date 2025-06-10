package com.ralphmarondev.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.domain.model.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SetupViewModel : ViewModel() {

    private val _passPhrase = MutableStateFlow("")
    val passPhrase = _passPhrase.asStateFlow()

    private val _confirmPassPhrase = MutableStateFlow("")
    val confirmPassPhrase = _confirmPassPhrase.asStateFlow()

    private val _fullName = MutableStateFlow("")
    val fullName = _fullName.asStateFlow()

    private val _computerName = MutableStateFlow("")
    val computerName = _computerName.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    private val _requirePasswordOnLogin = MutableStateFlow(true)
    val requirePasswordOnLogin = _requirePasswordOnLogin.asStateFlow()

    private val _encryptionResponse = MutableStateFlow<Result?>(null)
    val encryptionResponse = _encryptionResponse.asStateFlow()


    fun onPassPhraseValueChange(value: String) {
        _passPhrase.value = value
    }

    fun onConfirmPassPhraseValueChange(value: String) {
        _confirmPassPhrase.value = value
    }

    fun onFullNameValueChange(value: String) {
        _fullName.value = value
    }

    fun onComputerValueChange(value: String) {
        _computerName.value = value
    }

    fun onUsernameValueChange(value: String) {
        _username.value = value
    }

    fun onPasswordValueChange(value: String) {
        _password.value = value
    }

    fun onConfirmPasswordValueChange(value: String) {
        _confirmPassword.value = value
    }

    fun setRequirePasswordOnLogin(value: Boolean) {
        _requirePasswordOnLogin.value = value
    }

    fun checkPassPhrase() {
        if (_passPhrase.value.trim().isBlank() || _confirmPassPhrase.value.trim().isBlank()) {
            _encryptionResponse.value = Result(
                success = false,
                message = "Passphrase cannot be empty."
            )
            return
        }

        if (_passPhrase.value.trim() != _confirmPassPhrase.value.trim()) {
            _encryptionResponse.value = Result(
                success = false,
                message = "Passphrase does not matched."
            )
            return
        }

        _encryptionResponse.value = Result(
            success = true,
            message = "Passphrase is valid."
        )
    }

    fun resetEncryptionResponse() {
        viewModelScope.launch {
            delay(3000)
            _encryptionResponse.value = null
        }
    }

    fun onboardingCompleted() {

    }
}