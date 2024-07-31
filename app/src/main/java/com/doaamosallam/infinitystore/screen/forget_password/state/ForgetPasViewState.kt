package com.doaamosallam.infinitystore.screen.forget_password.state

sealed class ForgetPasViewState {
    object Idel : ForgetPasViewState()
    object Loading : ForgetPasViewState()
    data class Success(val message: String) : ForgetPasViewState()
    data class Error(val message: String) : ForgetPasViewState()
    data class Content(val email: String = "") : ForgetPasViewState()
}