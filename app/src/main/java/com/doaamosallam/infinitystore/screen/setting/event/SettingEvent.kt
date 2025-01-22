package com.doaamosallam.infinitystore.screen.setting.event

sealed class SettingEvent {
    data class IsLoading(val isLoading: Boolean) : SettingEvent()
    data class OnError(val message: String) : SettingEvent()
    data class OnSuccess(val message: String) : SettingEvent()

}