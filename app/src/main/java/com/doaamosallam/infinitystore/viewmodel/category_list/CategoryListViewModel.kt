package com.doaamosallam.infinitystore.viewmodel.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.usecase.CategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val categoryListUseCase: CategoryListUseCase
): ViewModel() {
    private val _viewState = MutableStateFlow<CategoryListViewState>(CategoryListViewState.Idle)
    val viewState: StateFlow<CategoryListViewState> = _viewState

    init {
        fetchCategoryList()
    }

    fun handleIntent(event: CategoryListIntent) {
        when (event) {
            is CategoryListIntent.GetCategoryList -> fetchCategoryList()
        }
    }

    private fun fetchCategoryList() = viewModelScope.launch {
        _viewState.value = CategoryListViewState.Loading
        try {
            val result = categoryListUseCase.getAllCategoryList()
            _viewState.value = CategoryListViewState.Success(result)
        } catch (e: Exception) {
            _viewState.value = CategoryListViewState.Error(e.message ?: "An error occurred")
        }
    }


}