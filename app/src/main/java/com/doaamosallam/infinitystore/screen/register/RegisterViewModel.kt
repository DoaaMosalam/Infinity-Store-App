package com.doaamosallam.infinitystore.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.usecase.RegisterUseCase
import com.doaamosallam.infinitystore.screen.register.event.RegisterEvent
import com.doaamosallam.infinitystore.screen.register.state.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<RegisterUiState>(RegisterUiState.Content())
    val viewState: StateFlow<RegisterUiState> get() = _viewState

    //process
    fun handleIntent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> register(event)
        }
    }

    private fun register(
        event: RegisterEvent.Register
    ) = viewModelScope.launch {
        _viewState.value = RegisterUiState.Loading
        try {
            registerUseCase.RegisterUser(
                Register(
                    name = event.name,
                    phone = event.phone,
                    email = event.email,
                    password = event.password,
                    confirmPassword = event.confirmPassword
                )
            )
            _viewState.value = RegisterUiState.Success(
                Register(
                    name = event.name,
                    phone = event.phone,
                    email = event.email,
                    password = event.password,
                    confirmPassword = event.confirmPassword
                )
            )
        } catch (e: Exception) {
            _viewState.value = RegisterUiState.Error("An error occurred: ${e.message}")
        }
    }

    fun onNameChange(newName: String) {
        val currentState = _viewState.value
        if (currentState is RegisterUiState.Content) {
            _viewState.value = currentState.copy(name = newName)
        }
    }

    fun onPhoneChange(newPhone: String) {
        val currentState = _viewState.value
        if (currentState is RegisterUiState.Content) {
            _viewState.value = currentState.copy(phone = newPhone)
        }
    }

    fun onEmailChange(newEmail: String) {
        val currentState = _viewState.value
        if (currentState is RegisterUiState.Content) {
            _viewState.value = currentState.copy(email = newEmail)
        }
    }


    fun onPasswordChange(newPassword: String) {
        val currentState = _viewState.value
        if (currentState is RegisterUiState.Content) {
            _viewState.value = currentState.copy(password = newPassword)
        }
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        val currentState = _viewState.value
        if (currentState is RegisterUiState.Content) {
            _viewState.value = currentState.copy(confirmPassword = newConfirmPassword)
        }

    }


    // Register by MVVM
//    private val _errorMessage = MutableSharedFlow<String>()
//    val errorMessage: SharedFlow<String> get() = _errorMessage
//
//    private val _registerState = MutableSharedFlow<RequestStatus<Register>>()
//    val registerState: MutableSharedFlow<RequestStatus<Register>> get() = _registerState
//
//    fun register(register: Register) = viewModelScope.launch {
//        try {
//            val result = registerUseCase.RegisterUser(register)
//            if (result != null) {
//                _registerState.collect { user ->
//                    _registerState.emit(user)
//                }
//            }
//        } catch (e: Exception) {
//            _errorMessage.emit("An error occurred:${e.message}")
//        }
//    }
}