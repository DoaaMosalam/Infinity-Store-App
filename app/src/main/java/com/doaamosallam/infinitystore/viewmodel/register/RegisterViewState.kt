package com.doaamosallam.infinitystore.viewmodel.register

import com.doaamosallam.domain.models.Register


sealed class RegisterViewState {
    object Idle : RegisterViewState()
    object Loading : RegisterViewState()
    data class Success(val register: Register) : RegisterViewState()
    data class Error(val message: String) : RegisterViewState()
    data class Content(
        val name: String = "",
        val phone: String = "",
        val email: String = "",
        val password: String = "",
        val confirmPassword: String = ""
    ) : RegisterViewState()

}