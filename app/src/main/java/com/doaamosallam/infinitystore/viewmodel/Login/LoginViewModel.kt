package com.doaamosallam.infinitystore.viewmodel.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.Login
import com.doaamosallam.domain.usecase.LoginUseCase
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
    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Content())
    val viewState: StateFlow<LoginViewState> get() = _viewState.asStateFlow()

    // process
    fun handleIntent(event: LoginIntent) {
        when (event) {
            is LoginIntent.Login -> login(event.email, event.password)
        }

    }

    //reduce
    private fun login(email: String, password: String) = viewModelScope.launch {
        Log.d("Login", "Login is Success : $email $password")
        _viewState.value = LoginViewState.Loading
        try {
            val result = loginUseCase.LoginUser(Login(email, password))
            if (result != null) {

                _viewState.value = LoginViewState.Success(result)

            } else {
                _viewState.value = LoginViewState.Error("Login Failed")
            }

        } catch (e: Exception) {
            _viewState.value = LoginViewState.Error("An error occurred:${e.message}")
        }
    }

    fun onEmailChange(newEmail: String) {
        // used update
        Log.d("Email", "onEmailChange: $newEmail")
        val currentState = _viewState.value
        if (currentState is LoginViewState.Content) {
            _viewState.value = currentState.copy(email = newEmail)
        }
    }

    fun onPasswordChange(newPassword: String) {
        val currentState = _viewState.value
        if (currentState is LoginViewState.Content) {
            _viewState.value = currentState.copy(password = newPassword)
        }
    }

}