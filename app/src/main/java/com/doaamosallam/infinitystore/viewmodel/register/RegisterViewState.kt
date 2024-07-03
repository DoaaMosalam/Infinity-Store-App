package com.doaamosallam.infinitystore.viewmodel.register

import com.doaamosallam.domain.models.Register


sealed class RegisterViewState {
    object  Idle:RegisterViewState()
    object Loading:RegisterViewState()
    data class Success(val register: Register):RegisterViewState()
    data class Error(val message:String):RegisterViewState()
}