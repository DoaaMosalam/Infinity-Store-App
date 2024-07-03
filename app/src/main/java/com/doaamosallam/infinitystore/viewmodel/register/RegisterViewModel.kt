package com.doaamosallam.infinitystore.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.Register
import com.doaamosallam.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
):ViewModel() {
    private val _viewState = MutableStateFlow<RegisterViewState>(RegisterViewState.Idle)
    val viewState: StateFlow<RegisterViewState> get() = _viewState

    //process
    fun handleIntent(event: RegisterIntent) {
        when (event) {
            is RegisterIntent.Register -> register(event)
        }

    }

    private fun register(
        event: RegisterIntent.Register
    ) = viewModelScope.launch {
        _viewState.value = RegisterViewState.Loading
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
            _viewState.value = RegisterViewState.Success(
                Register(
                    name = event.name,
                    phone = event.phone,
                    email = event.email,
                    password = event.password,
                    confirmPassword = event.confirmPassword
                )
            )
        } catch (e: Exception) {
            _viewState.value = RegisterViewState.Error("An error occurred: ${e.message}")
        }
    }



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