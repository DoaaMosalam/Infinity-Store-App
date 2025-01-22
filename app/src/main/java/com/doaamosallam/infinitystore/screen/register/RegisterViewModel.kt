package com.doaamosallam.infinitystore.screen.register

import android.util.Log
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.auth.Register
import com.doaamosallam.domain.usecase.ProfileUseCase
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
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> get() = _uiState


    private fun emitEvent(event: RegisterEvent) {
        _uiState.value = when (event) {
            is RegisterEvent.Register -> _uiState.value.copy(
                register = event.register
            )

            is RegisterEvent.SetLoading -> _uiState.value.copy(
                loading = event.isLoading
            )

            is RegisterEvent.SetError -> _uiState.value.copy(
                loading = false,
                error = event.message,
                success = false
            )

            is RegisterEvent.SetSuccess -> _uiState.value.copy(
                loading = false,
                error = "",
            )

            is RegisterEvent.UpdateName -> _uiState.value.copy(
                register = _uiState.value.register.copy(name = event.name)
            )

            is RegisterEvent.UpdateEmail -> _uiState.value.copy(
                register = _uiState.value.register.copy(email = event.email)
            )

            is RegisterEvent.UpdatePhone -> _uiState.value.copy(
                register = _uiState.value.register.copy(phone = event.phone)
            )

            is RegisterEvent.UpdatePassword -> _uiState.value.copy(
                register = _uiState.value.register.copy(password = event.password)
            )

            is RegisterEvent.UpdatePasswordInDb -> {
                viewModelScope.launch {
                    try {
                        emitEvent(RegisterEvent.SetLoading(true))
                        registerUseCase.updatePassword(event.email, event.newPassword)
                        emitEvent(RegisterEvent.SetSuccess("Password updated successfully"))
                    } catch (e: Exception) {
                        emitEvent(RegisterEvent.SetError(e.message ?: "An error occurred"))
                    } finally {
                        emitEvent(RegisterEvent.SetLoading(false))
                    }
                }
                _uiState.value
            }
        }
    }

    fun registerUser() {
        val currentState = _uiState.value
        if (validateFields(currentState)) {
            val register = Register(
                name = currentState.name,
                phone = currentState.phone,
                email = currentState.email,
                password = currentState.password,
                confirmPassword = currentState.confirmPassword
            )
            viewModelScope.launch {
                try {
                    emitEvent(RegisterEvent.SetLoading(true))
                   registerUseCase.registerUser(register)
                    emitEvent(RegisterEvent.Register(register))
                    emitEvent(RegisterEvent.SetSuccess("Registration successful"))
                } catch (e: Exception) {
                    emitEvent(RegisterEvent.SetError(e.message ?: "An error occurred"))
                } finally {
                    emitEvent(RegisterEvent.SetLoading(false))
                }
            }
        } else {
            emitEvent(RegisterEvent.SetError("Please fill in all fields correctly"))
        }
    }

    private fun validateFields(state: RegisterUiState): Boolean {
        return state.name.isNotEmpty() &&
                state.phone.length == 11 &&
                PatternsCompat.EMAIL_ADDRESS.matcher(state.email).matches() &&
                state.password.length >= 6 &&
                state.password == state.confirmPassword
    }

    fun onNameChange(newName: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(name = newName)
    }

    fun onPhoneChange(newPhone: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(phone = newPhone)
    }

    fun onEmailChange(newEmail: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(email = newEmail)

    }


    fun onPasswordChange(newPassword: String) {
        val currentState = _uiState.value

        _uiState.value = currentState.copy(password = newPassword)
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(confirmPassword = newConfirmPassword)

    }
}
