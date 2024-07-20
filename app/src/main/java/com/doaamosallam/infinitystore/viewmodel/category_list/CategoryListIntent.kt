package com.doaamosallam.infinitystore.viewmodel.category_list

sealed class CategoryListIntent {
    data object GetCategoryList : CategoryListIntent()

}