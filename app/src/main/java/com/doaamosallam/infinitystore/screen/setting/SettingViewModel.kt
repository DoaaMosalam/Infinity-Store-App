package com.doaamosallam.infinitystore.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.profile.ImagesUser
import com.doaamosallam.domain.usecase.ProfileUseCase
import com.doaamosallam.infinitystore.screen.profile.event.ProfileEvent
import com.doaamosallam.infinitystore.screen.setting.event.SettingEvent
import com.doaamosallam.infinitystore.screen.setting.state.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
): ViewModel(){

   private val _uiState = MutableStateFlow(SettingUiState())
    val uiState :StateFlow<SettingUiState> get() = _uiState


    fun emitEvent(event: SettingEvent){
        when(event){
            is SettingEvent.IsLoading->{
                _uiState.value = _uiState.value.copy(
                    loading = event.isLoading
                )
            }
            is SettingEvent.OnError-> {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = event.message,
                    success = false
                )
            }
            is SettingEvent.OnSuccess -> {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    success = true
                )
            }
        }
    }

//    fun getImages() = viewModelScope.launch {
//        try {
//            emitEvent(SettingEvent.IsLoading(true))
//            val result = profileUseCase.getImageFromProfile()
//            emitEvent(SettingEvent.GetImages(result))
//            emitEvent(SettingEvent.IsLoading(false))
//
//        } catch (e: Exception) {
//            emitEvent(SettingEvent.OnError(e.message ?: "An error occurred"))
//        }
//    }
}