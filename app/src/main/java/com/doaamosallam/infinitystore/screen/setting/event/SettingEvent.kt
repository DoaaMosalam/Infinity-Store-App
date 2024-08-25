package com.doaamosallam.infinitystore.screen.setting.event

import com.doaamosallam.domain.models.profile.ImagesUser

sealed class SettingEvent {
    data class IsLoading(val isLoading: Boolean) : SettingEvent()
    data class OnError(val message: String) : SettingEvent()
    data class OnSuccess(val message: String) : SettingEvent()

}