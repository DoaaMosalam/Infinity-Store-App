package com.doaamosallam.infinitystore.screen.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.infinitystore.screen.login.event.LoginEvent
import com.doaamosallam.infinitystore.screen.login.navigation.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Content())
    val uiState: StateFlow<LoginUiState> get() = _uiState.asStateFlow()

    // process
    fun handleIntent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> login(event.email, event.password)
        }

    }

    //reduce
    private fun login(email: String, password: String) = viewModelScope.launch {

        _uiState.value = LoginUiState.Loading
        try {
            val result = loginUseCase.LoginUser(Login(email, password))
            if (result != null) {
                _uiState.value = LoginUiState.Success(result)

            } else {
                _uiState.value = LoginUiState.Error("Login Failed")
            }
        } catch (e: Exception) {
            _uiState.value = LoginUiState.Error("An error occurred:${e.message}")
        }
    }

    fun onEmailChange(newEmail: String) {
        // used update
        Log.d("Email", "onEmailChange: $newEmail")
        val currentState = _uiState.value
        if (currentState is LoginUiState.Content) {
            _uiState.value = currentState.copy(email = newEmail)
        }
    }

    fun onPasswordChange(newPassword: String) {
        val currentState = _uiState.value
        if (currentState is LoginUiState.Content) {
            _uiState.value = currentState.copy(password = newPassword)
        }
    }

}