package com.doaamosallam.infinitystore.viewmodel.categories

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.usecase.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel(){

    private val _viewState = MutableStateFlow<CategoriesViewState>(CategoriesViewState.Loading)
    val viewState: StateFlow<CategoriesViewState> = _viewState

    init {
        fetchCategories()
    }
    fun handleIntent(intent: CategoriesIntent) {
        when (intent) {
            is CategoriesIntent.GetCategories -> fetchCategories()
        }
    }
    private fun fetchCategories() = viewModelScope.launch{
        _viewState.value = CategoriesViewState.Loading
        try {
            val result = categoriesUseCase.getAllCategories()
            Log.d("Categories", "Categories fetched successfully: ${result}")
            _viewState.value = CategoriesViewState.Success(result)
        } catch (e: Exception) {
            Log.e("Categories", "Error fetching categories: ${e.message}")
            _viewState.value = CategoriesViewState.Error(e.message ?: "An error occurred")
        }

    }
}