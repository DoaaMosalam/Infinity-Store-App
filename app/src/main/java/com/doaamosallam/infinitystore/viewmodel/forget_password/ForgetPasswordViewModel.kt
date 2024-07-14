package com.doaamosallam.infinitystore.viewmodel.forget_password

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.infinitystore.common.Resource
import com.doaamosallam.infinitystore.data.FirebaseAuthRepository
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
   val authRepository: FirebaseAuthRepository
): ViewModel() {
    private val _forgetViewState = MutableStateFlow<ForgetPasViewState>(ForgetPasViewState.Content())
    val forgetViewState: StateFlow<ForgetPasViewState> get() = _forgetViewState.asStateFlow()


    val email = MutableStateFlow("")
    // process

    fun handleIntent(event:ForgetPasswordIntent){
        when(event){
            is ForgetPasswordIntent.ForgetPassword -> sendUpdatePasswordEmail()
        }

    }
    private fun sendUpdatePasswordEmail() = viewModelScope.launch{
        authRepository.sendUpdatePasswordEmail(email.value).collect{result->
            when(result){
                is Resource.Loading -> _forgetViewState.value = ForgetPasViewState.Loading
                is Resource.Success -> _forgetViewState.value = ForgetPasViewState.Success(result.data ?: "Success")
                is Resource.Error -> _forgetViewState.value = ForgetPasViewState.Error(result.data ?: "An error occurred")
            }

        }
//        _forgetViewState.value = ForgetPasViewState.Loading
//        try {
//            val result = authRepository.sendUpdatePasswordEmail(email.value)
//            _forgetViewState.value = ForgetPasViewState.Success(result.toString())
//
//
//        }catch (e:Exception){
//            _forgetViewState.value = ForgetPasViewState.Error("An error occurred:${e.message}")
//        }
    }

    fun onEmailChange(newEmail: String) {
        // used update
        Log.d("Email", "onEmailChange: $newEmail")
        val currentState = _forgetViewState.value
        if (currentState is ForgetPasViewState.Content) {
            _forgetViewState.value = currentState.copy(email = newEmail)
        }
    }

}


