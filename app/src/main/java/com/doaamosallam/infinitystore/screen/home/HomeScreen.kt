@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.doaamosallam.infinitystore.screen.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.CategoryItem
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.compose.IconButtonHome
import com.doaamosallam.infinitystore.compose.ProductItem
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.compose.TextGeneral
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.home.navigation.MenuItem
import com.doaamosallam.infinitystore.screen.home.state.HomeUiState
import com.doaamosallam.infinitystore.screen.product_details.ProductDetailsScreen
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContainer(navController: NavController) {
    /*
* Navigation Drawer MenuScreen  (ModalNavigationDrawer)
* Search bar to search products
* Category list show all products items
* Products list show all products
* */
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    /*
* Drawable Navigation Drawer
* */
    DismissibleNavigationDrawer(
        drawerContent = {
            DismissibleDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                ) {
                    // Display user information in Menu Navigation Drawer
                    DisplayInfoUser(navController)
                    // Display menu items List in Menu Navigation Drawer
                    val items = menuItemsList()

                    val selectedItemIndex = rememberSaveable { mutableIntStateOf(0) }
                    items.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item.title) },
                            selected = index == selectedItemIndex.intValue,
                            onClick = {
                                selectedItemIndex.intValue = index
                                coroutineScope.launch { drawerState.close() }
                                when (item.title) {
                                    "Profile" -> navController.navigate(Screen.ProfileScreen.route)
                                    "Favorite" -> navController.navigate(Screen.FavoriteScreen.route)
                                    "Settings" -> navController.navigate(Screen.SettingScreen.route)
                                    "Logout" -> navController.navigate(Screen.Login.route)

                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex.intValue) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            badge = {
                                item.badgeCount?.let {
                                    Text(text = it.toString())
                                }
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        },
        drawerState = drawerState
    ) {
        //  execute all action in HomeContainer
        ExecuteAllAction(navController,
            uiState,
            coroutineScope,
            drawerState,
            viewModel,
            selectedProduct,
            onProductSelected = { product -> selectedProduct = product },
            onProductDeselected = { selectedProduct = null }
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ExecuteAllAction(
    navController: NavController,
    uiState: HomeUiState,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    viewModel: HomeViewModel,
    selectedProduct: Product?,
    onProductSelected: (Product) -> Unit,
    onProductDeselected: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        if (uiState.error.isNotEmpty())
            Text(text = uiState.error)

        SharedTransitionLayout {
            AnimatedContent(
                targetState = selectedProduct == null,
                label = "transition"
            ) { isProductListVisible ->
                if (isProductListVisible) {
                    HomeScreen(
                        products = uiState.products,
                        transitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@AnimatedContent,
                        categoryList = uiState.categories,
                        onClickMenu = {
                            coroutineScope.launch { drawerState.open() }
                        },
                        onClickProfile = {
                            navController.navigate(Screen.ProfileScreen.route)
                        },
                        search = uiState.search,
                        onSearchChange = viewModel::onSearchEvent,
                        onClickProduct = onProductSelected,
                        onClickCart = viewModel::onAddProductToCart
                    )
                }
                else {
                    selectedProduct?.let {
                        ProductDetailsScreen(
                            product = selectedProduct,
                            onClickBack = { onProductDeselected() },
                            onClickFavorite = viewModel::onAddProductToFavorite,
                            onClickCart = viewModel::onAddProductToCart

                        )
                    }
                }

            }
        }


        FullScreenLoading(
            modifier = Modifier.fillMaxSize(),
            isLoading = uiState.isLoading,
        )
    }
}


@Composable
private fun HomeScreen(
    products: List<Product>,
    onClickProduct: (Product) -> Unit,
    transitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    categoryList: List<CategoryList>,
    onClickMenu: () -> Unit,
    onClickProfile: () -> Unit,
    search: String,
    onSearchChange: (String) -> Unit,
    onClickCart: (Product) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButtonHome(
                onClick = { onClickMenu() },
                painter = painterResource(id = R.drawable.menu),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            SpacerGeneral(modifier = Modifier.weight(1f))

            IconButtonHome(
                onClick = { onClickProfile() },
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        // header title and subtitle
        HeaderHome(
            title = "Explore",
            subtitle = "Best trendy collection!",
            color = MaterialTheme.colorScheme.onBackground
        )
        // search bar
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            value = search,
            onValueChange = { onSearchChange(it) },

            label = { Text(text = stringResource(R.string.search)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
            ),
            visualTransformation = VisualTransformation.None
        )

        SpacerGeneral(modifier = Modifier.height(16.dp))
        // display category
        DisplayCategory(categoryList)
        SpacerGeneral(modifier = Modifier.height(16.dp))
        // display products
        DisplayProducts(
            products = products,
            onClickProduct = onClickProduct,
            onClickCart = onClickCart,
            sharedTransitionScope = transitionScope,
            animatedVisibilityScope = animatedVisibilityScope
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayProducts(
    products: List<Product>,
    onClickProduct: (Product) -> Unit,
    onClickCart: (Product) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,

    ) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = products
        ) { product ->

            ProductItem(
                product = product,
                onClickProduct = { onClickProduct(product) },
                onClickCart = onClickCart,
                modifier = Modifier.animateItemPlacement(),
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope

            )
        }

    }
}

@Composable
private fun menuItemsList(): List<MenuItem> {
    val items = listOf(
        MenuItem(
            title = "Profile",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.person_outline_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.person_outline_24)
        ),
        MenuItem(
            title = "Favorite",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.outline_bookmark_border_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.outline_bookmark_border_24),
        ),
        MenuItem(
            title = "Settings",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_settings_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.baseline_settings_24)
        ),
        MenuItem(
            title = "Logout",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.outline_logout_24),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.outline_logout_24)
        )
    )
    return items
}

@Composable
private fun DisplayInfoUser(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextGeneral(
            title = stringResource(R.string.menu),
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Merri,
            color = PrimaryColor
        )
        SpacerGeneral(modifier = Modifier.height(30.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.person_outline_24), // replace with your profile picture resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clickable { navController.navigate(Screen.ProfileScreen.route) }
                    .size(80.dp)
                    .background(Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                TextGeneral(
                    title = "Name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                TextGeneral(
                    title = "Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun DisplayCategory(categoryList: List<CategoryList>) {
    val collectionsList = listOf("All") + categoryList.map { it.name }
    var selectedCollection by remember { mutableStateOf("All") }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 6.dp)
    ) {
        items(collectionsList) { collection ->
            CategoryItem(
                collection = collection,
                isSelected = collection == selectedCollection,
                onClick = { selected ->
                    selectedCollection = selected
                }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewHomeScreen() {
//    HomeScreen(
//        products = emptyList(),
//        transitionScope = this@SharedTransitionLayout ,
//        animatedVisibilityScope = this@AnimatedContent,
//        categoryList = emptyList(),
//        onClickMenu = {},
//        onClickProfile = {},
//        search = "",
//        onSearchChange = {},
//        onClickProduct = {},
//        onClickCart = {}
//    )
//}
