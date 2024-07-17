package com.doaamosallam.infinitystore.viewmodel.home

sealed class HomeIntent {
    data object GetProducts : HomeIntent()
}