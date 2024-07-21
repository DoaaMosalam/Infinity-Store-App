package com.doaamosallam.infinitystore.screen.register.state

import com.doaamosallam.domain.models.auth.Register


sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    data class Success(val register: Register) : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
    data class Content(
        val name: String = "",
        val phone: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = ""
    ) : RegisterUiState()

}