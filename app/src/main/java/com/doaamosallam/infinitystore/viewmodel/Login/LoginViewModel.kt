
package com.doaamosallam.infinitystore.viewmodel.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.Login
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.infinitystore.util.RequestStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
):ViewModel() {
    private val _viewState = MutableStateFlow<LoginViewState>(LoginViewState.Idle)
    val viewState:StateFlow<LoginViewState> get() = _viewState
    // process
    fun handleIntent(event:LoginIntent){
        when(event){
            is LoginIntent.Login -> login(event.email,event.password)
        }

    }
    //reduce
    private fun login(email: String, password: String)= viewModelScope.launch {
        _viewState.value = LoginViewState.Loading
        try {
            val result = loginUseCase.LoginUser(Login(email, password))
            if (result!=null){
                _viewState.value = LoginViewState.Success(result)
            }else{
                _viewState.value = LoginViewState.Error("Login Failed")
            }

        }catch (e:Exception){
            _viewState.value = LoginViewState.Error("An error occurred:${e.message}")
        }
    }




//    private val _loginState = MutableSharedFlow<RequestStatus<Login>>()
//    val loginState :SharedFlow<RequestStatus<Login>> get()= _loginState
//
//    private val _errorMessage = MutableSharedFlow<String>()
//    val errorMessage:SharedFlow<String> get() = _errorMessage
//
//    fun login(login: Login)=
//        viewModelScope.launch {
//            try {
//                val result = loginUseCase.LoginUser(login)
//                if (result!=null){
//                    _loginState.collect{ user->
//                        _loginState.emit(user)
//                    }
//                }else{
//                   _errorMessage.emit("Login Failed")
//                }
//            }catch (e:Exception){
//                _errorMessage.emit("An error occurred:${e.message}")
//
//            }
//        }
}