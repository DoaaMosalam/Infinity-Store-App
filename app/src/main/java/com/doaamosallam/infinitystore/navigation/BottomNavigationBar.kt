package com.doaamosallam.infinitystore.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.doaamosallam.infinitystore.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedItem = remember { mutableStateOf(Screen.HomeScreen.route) }

    BottomAppBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        val items = listOf(
            Screen.HomeScreen,
            Screen.CategoryScreen,
            Screen.FavoriteScreen,
            Screen.CartScreen,
            Screen.ProfileScreen
        )

        items.forEach { screen ->
            IconButton(
                onClick = {
                    selectedItem.value = screen.route
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = screen.icon ?: R.drawable.outline_home_24),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    tint = if (selectedItem.value == screen.route) Color.Black else Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    BottomNavigationBar(navController = rememberNavController())
}