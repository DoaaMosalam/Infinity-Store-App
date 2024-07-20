package com.doaamosallam.infinitystore.viewmodel.categories

sealed class CategoriesIntent {
    data object GetCategories : CategoriesIntent()

}