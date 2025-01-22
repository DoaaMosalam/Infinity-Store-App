@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalSharedTransitionApi::class)

package com.doaamosallam.infinitystore.compose

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.ui.theme.BABA_BLUE
import com.doaamosallam.infinitystore.ui.theme.BLACK
import com.doaamosallam.infinitystore.ui.theme.DARK_GRAY
import com.doaamosallam.infinitystore.ui.theme.LIGHT_GRAY
import com.doaamosallam.infinitystore.ui.theme.MELON_MELODY
import com.doaamosallam.infinitystore.ui.theme.PrimaryColor
import com.doaamosallam.infinitystore.ui.theme.WHITE

/*
* Display Product Item in Home Screen include ( Image, title, price, description)
*
* */
@Composable
fun ProductItem(
    product: Product,
    onClickProduct: () -> Unit,
    onClickCart: (Product) -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp)
            .clickable(onClick = onClickProduct),
        colors = CardDefaults.cardColors(
            MELON_MELODY
        )

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(7.dp)
                .clickable(onClick = onClickProduct)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClickProduct() },
                contentAlignment = Alignment.BottomEnd
            ) {
                with(sharedTransitionScope) {
                    AsyncImage(
                        model = product.thumbnail,
                        contentDescription = null,
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = "image"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .fillMaxSize()
                            .height(200.dp)
                            .background(WHITE),
                        contentScale = ContentScale.Crop
                    )
                }
                IconButtonCart(
                    onClick = { onClickCart(product) },
                    painter = painterResource(id = R.drawable.shopping_bag_24),
                    contentDescription = null,
                    tint = WHITE,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Black, shape = CircleShape)
                        .padding(4.dp)
                )

            }
            SpacerGeneral(modifier = Modifier.height(8.dp))
            TextGeneral(
                title = "\$${product.price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = BLACK,
                fontFamily = FontFamily.Default,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            SpacerGeneral(modifier = Modifier.height(8.dp))
            TextGeneral(
                title = product.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = DARK_GRAY,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}


/* Display List of Name Categories in Home Screen */

@Composable
fun CategoryItem(collection: String, isSelected: Boolean, onClick: (String) -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFFFF7A00) else Color.White
    val textColor = if (isSelected) WHITE else BLACK

    Text(
        text = collection,
        color = textColor,
        modifier = Modifier
            .padding(5.dp)
            .clickable { onClick(collection) }
            .background(
                color = if (isSelected) Color(0xFFFF7A00) else Color(0xFFF5F5F5),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),

        style = MaterialTheme.typography.bodyLarge.copy(background = backgroundColor)
    )
}

/* Display all  Categories products in Categories Screen  */
@Composable
fun CategoriesItem(category: CategoriesItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            MELON_MELODY
        )

    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(7.dp)
                .clickable(onClick = onClick),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxHeight(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = rememberImagePainter(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(300.dp)
                        .width(300.dp)
                        .background(Color.White)
                )
                SpacerGeneral(modifier = Modifier.height(8.dp))
                Text(
                    text = category.name,
                    modifier = Modifier.padding(15.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BABA_BLUE,
                    fontFamily = FontFamily.Default
                )
            }
        }
    }
}

/*
* Display Cart Item in Cart Screen include ( Image, title, price, quantity)
* delete  product item from cart
* */
@Composable
fun CartItem(
    cart: CartProduct,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        colors = CardDefaults.cardColors(
            MELON_MELODY
        )
    ) {
        Row(
            modifier = modifier
                .scrollable(
                    orientation = Orientation.Horizontal,
                    enabled = true,
                    state = rememberScrollState()
                )
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = cart.thumbnail,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White)
                    .padding(8.dp)
            )
//            Image(
//                painter = rememberImagePainter(data = cart.thumbnail),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(80.dp)
//                    .background(Color.White)
//                    .padding(8.dp)
//            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                TextGeneral(
                    title = cart.title,
                    modifier = Modifier.weight(1f),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    color = BLACK
                )
                TextGeneral(
                    title = cart.discountPercentage.toString(),
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))
                TextGeneral(
                    title = "$${cart.price}",
                    modifier = Modifier.weight(1f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Default,
                    color = BLACK
                )
            }
            TextGeneral(
                title = stringResource(R.string.x, cart.quantity),
                modifier = Modifier.weight(1f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.width(25.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .size(60.dp)
                    .weight(1f)
                    .background(
                        colorResource(id = R.color.primary_color),
                        shape = CircleShape.copy(
                            topEnd = CornerSize(10.dp),
                            bottomEnd = CornerSize(10.dp),
                        )
                    )
                    .clickable { onClickDelete() },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_delete),
                    contentDescription = "Delete",
                    tint = colorResource(id = R.color.white),
                    modifier = Modifier.size(28.dp)
                )
            }

        }
    }
}

@Composable
fun DisplayTotalsItems_Price(itemsTotal: Int, priceTotal: Double) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TextGeneral(
            title = stringResource(R.string.total_items, itemsTotal),
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = LIGHT_GRAY
        )

        TextGeneral(
            title = stringResource(R.string.stander_delivery_fee_12_00),
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = LIGHT_GRAY
        )
        TextGeneral(
            title = stringResource(R.string.total_payment, "%.2f".format(priceTotal)),
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = LIGHT_GRAY

        )
    }
}

/*
* Design Details Items
* List Size
* Button add to cart
*  */

@Composable
fun DetailsItems(
    product: Product,
    sizes: List<String>,
    selectedSize: MutableState<String>,
    onClickCart: (Product) -> Unit,
) {
    AsyncImage(
        model = product.thumbnail,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .background(MELON_MELODY, shape = RoundedCornerShape(20.dp))
            .padding(8.dp)
            .clip(RoundedCornerShape(30.dp))
    )

    SpacerGeneral(modifier = Modifier.height(16.dp))
    Text(
        text = product.title,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )

    SpacerGeneral(modifier = Modifier.height(8.dp))

    Text(
        text = product.description,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Normal
    )

    SpacerGeneral(modifier = Modifier.height(8.dp))

    Text(
        text = "Size",
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )

    SpacerGeneral(modifier = Modifier.height(8.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizes.forEach { size ->
            SizeChip(
                size = size,
                isSelected = selectedSize.value == size,
                onClick = { selectedSize.value = size }
            )
        }
    }

    SpacerGeneral(modifier = Modifier.height(20.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = { onClickCart(product) },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(text = "Add To Cart", color = Color.White)
        }
    }
}

/* */
@Composable
fun SizeChip(size: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        color = if (isSelected) Color.Black else Color.Transparent,
        shape = RoundedCornerShape(10),
        border = BorderStroke(1.dp, if (isSelected) Color.Black else Color.Gray)
    ) {
        Text(
            text = size,
            color = if (isSelected) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}


@Composable
fun ProductFavoriteItem(
    product: FavoriteProduct,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isDelete by remember { mutableStateOf(false) }
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = MELON_MELODY
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(WHITE)
                        .padding(8.dp)
                )
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.DarkGray.copy(alpha = 0.2f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            isDelete = !isDelete
                            onClickDelete()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isDelete) R.drawable.baseline_delete_24
                                else R.drawable.outline_delete
                            ),
                            contentDescription = stringResource(R.string.bookmark),
                            tint = if (isDelete) Color.Red else Color.DarkGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "\$${product.price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = BLACK,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = DARK_GRAY,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}




