package com.doaamosallam.infinitystore.screen.home.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon:ImageVector,
    val badgeCount: Int? = null,

)