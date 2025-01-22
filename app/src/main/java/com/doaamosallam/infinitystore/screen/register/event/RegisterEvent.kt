package com.doaamosallam.infinitystore.screen.register.event

sealed class RegisterEvent {
    data class Register(val register: com.doaamosallam.domain.models.auth.Register) : RegisterEvent()

    data class UpdateName(val name: String) : RegisterEvent()
    data class UpdateEmail(val email: String) : RegisterEvent()
    data class UpdatePhone(val phone: String) : RegisterEvent()
    data class UpdatePassword(val password: String) : RegisterEvent()
    data class UpdatePasswordInDb(val email: String, val newPassword: String) : RegisterEvent()
    data class SetLoading(val isLoading: Boolean) : RegisterEvent()
    data class SetError(val message: String) : RegisterEvent()
    data class SetSuccess(val message: String) : RegisterEvent()
}