package com.doaamosallam.infinitystore.viewmodel.user.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.Register
import com.doaamosallam.domain.usecase.RegisterUseCase
import com.doaamosallam.infinitystore.util.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
):ViewModel() {
    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> get() = _errorMessage

    private val _registerState = MutableSharedFlow<RequestStatus<Register>>()
    val registerState: MutableSharedFlow<RequestStatus<Register>> get() = _registerState

    fun register(register: Register) = viewModelScope.launch {
        try {
            val result = registerUseCase.RegisterUser(register)
            if (result != null) {
                _registerState.collect { user ->
                    _registerState.emit(user)
                }
            }
        } catch (e: Exception) {
            _errorMessage.emit("An error occurred:${e.message}")
        }
    }
}