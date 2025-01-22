package com.doaamosallam.infinitystore.screen.register.state

import com.doaamosallam.domain.models.auth.Register

data class RegisterUiState(
    val register: Register = Register("", "", "", "", ""),
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)