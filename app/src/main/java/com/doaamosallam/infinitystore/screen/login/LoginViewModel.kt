package com.doaamosallam.infinitystore.screen.login

import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.auth.Login
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.infinitystore.screen.login.event.LoginEvent
import com.doaamosallam.infinitystore.screen.login.state.LoginUiState
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
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> get() = _uiState.asStateFlow()

    fun emitEvent(event: LoginEvent) {
        _uiState.value = when (event) {
            is LoginEvent.Login -> _uiState.value.copy(
                login = event.login
            )

            is LoginEvent.UpdateEmail -> _uiState.value.copy(
                login = _uiState.value.login.copy(email = event.email),
            )

            is LoginEvent.UpdatePassword -> _uiState.value.copy(
                login = _uiState.value.login.copy(password = event.password),
            )

            is LoginEvent.SetLoading -> _uiState.value.copy(
                loading = event.isLoading
            )

            is LoginEvent.SetError -> _uiState.value.copy(
                loading = false,
                error = event.message
            )

            is LoginEvent.SetSuccess -> _uiState.value.copy(
                loading = false,
                error = "",
                success = true
            )

        }
    }

    fun loginUser() {
        val currentState = _uiState.value
        if (validateFields(currentState)) {
            val login = Login(
                email = currentState.email,
                password = currentState.password
            )
            viewModelScope.launch {
                try {
                    emitEvent(LoginEvent.SetLoading(true))
                    loginUseCase.loginUser(login)
                    emitEvent(LoginEvent.Login(login))
                    Log.d("login", "Login successful")
                    emitEvent(LoginEvent.SetSuccess("Login successful"))
                } catch (e: Exception) {
                    emitEvent(LoginEvent.SetError(e.message ?: "An error occurred"))
                } finally {
                    emitEvent(LoginEvent.SetLoading(false))
                }
            }
        } else {
            emitEvent(LoginEvent.SetError("Please fill in all fields correctly"))
        }
    }

    private fun validateFields(state: LoginUiState): Boolean {
        return state.password.length >= 6 &&
                PatternsCompat.EMAIL_ADDRESS.matcher(state.email).matches()

    }

    fun onEmailChange(newEmail: String) {
        // used update
        Log.d("Email", "onEmailChange: $newEmail")
        val currentState = _uiState.value
        _uiState.value = currentState.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(password = newPassword)
    }
}