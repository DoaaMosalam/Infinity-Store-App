package com.doaamosallam.infinitystore.viewmodel.forget_password

sealed class ForgetPasViewState {
    object Idel : ForgetPasViewState()
    object Loading : ForgetPasViewState()
    data class Success(val message: String) : ForgetPasViewState()
    data class Error(val message: String) : ForgetPasViewState()
    data class Content(val email: String = "") : ForgetPasViewState()
}