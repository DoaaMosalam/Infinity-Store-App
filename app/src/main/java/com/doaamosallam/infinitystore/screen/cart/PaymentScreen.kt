package com.doaamosallam.infinitystore.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.CheckOut_PayNow
import com.doaamosallam.infinitystore.compose.DisplayTotalsItems_Price
import com.doaamosallam.infinitystore.compose.TextGeneral
import com.doaamosallam.infinitystore.compose.TextNote
import com.doaamosallam.infinitystore.compose.TopBarScreen
import com.doaamosallam.infinitystore.ui.theme.Merri
import com.doaamosallam.infinitystore.ui.theme.playWriteRegular


@Composable
fun PaymentContainer(navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    PaymentScreen(
        onClickBack = { navController.popBackStack() },
        deliveryAddress = "",
        deliveryTime = "",
        orderId = uiState.orderId,
        itemsTotal = uiState.totalItems,
        priceTotal = uiState.totalPrice,
        deliveryFee = 0.0,
        onPayClick = { /* Handle payment logic */ },
        onChangeAddressClick = { /* Handle change address logic */ },
        onAddVoucherClick = { /* Handle add voucher logic */ }
    )

}

@Composable
fun PaymentScreen(
    onClickBack: () -> Unit = {},
    deliveryAddress: String,
    deliveryTime: String,
    orderId: String,
    itemsTotal: Int,
    priceTotal: Double,
    deliveryFee: Double,
    onPayClick: () -> Unit,
    onChangeAddressClick: () -> Unit,
    onAddVoucherClick: () -> Unit
) {
    PaymentDisplay(
        onClickBack = onClickBack,
        deliveryAddress = deliveryAddress,
        deliveryTime = deliveryTime,
        orderId = orderId,
        itemsTotal = itemsTotal,
        priceTotal = priceTotal,
        deliveryFee = deliveryFee,
        onPayClick = onPayClick,
        onChangeAddressClick = onChangeAddressClick,
        onAddVoucherClick = onAddVoucherClick

    )

}

@Composable
fun PaymentDisplay(
    onClickBack: () -> Unit = {},
    deliveryAddress: String,
    deliveryTime: String,
    orderId: String,
    itemsTotal: Int,
    priceTotal: Double,
    deliveryFee: Double,
    onPayClick: () -> Unit,
    onChangeAddressClick: () -> Unit,
    onAddVoucherClick: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopBarScreen(
            modifier = Modifier.padding(top = 20.dp),
            onClickBack = onClickBack,
            text = "CheckOut",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            fontFamily = Merri,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Delivery Address",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Location",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = deliveryAddress,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = deliveryTime,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray
                )
            }
            TextButton(onClick = onChangeAddressClick) {
                TextGeneral(
                    "Change",
                    Modifier.padding(top = 5.dp),
                    12.sp,
                    FontWeight.Normal,
                    playWriteRegular,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextGeneral(
            "Payment Method",
            Modifier.padding(top = 5.dp),
            12.sp,
            FontWeight.Normal,
            playWriteRegular,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))

        DisplayPayment()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onAddVoucherClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("Add Voucher", color = Color.DarkGray)
        }
        NoteText(orderId)

        Spacer(modifier = Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                .padding(5.dp)
        ) {
            // Display total items and total price
            DisplayTotalsItems_Price(itemsTotal, priceTotal)

            Spacer(modifier = Modifier.height(16.dp))
            // PayNow button
            CheckOut_PayNow(
                onClick = { onPayClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        colorResource(id = R.color.primary_color),
                        shape = RoundedCornerShape(20.dp)
                    ),
                buttonText = stringResource(R.string.pay_now),
                buttonColor = colorResource(id = R.color.primary_color),
                textColor = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18,
                fontFamily = Merri,
                color = Color.White
            )
        }
    }
}

@Composable
private fun NoteText(orderId: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        TextNote(
            title = "Note:",
            modifier = Modifier.padding(8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Red,
        )
        TextNote(
            title = "Use your order id at the payment. Your Id #$orderId if you forget to put your order id we can’t confirm the payment.",
            modifier = Modifier.padding(8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Default,
            color = Color.Gray
        )
    }
}

@Composable
fun DisplayPayment() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(paymentMethods()) { method ->
            Image(
                painter = painterResource(id = method),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


fun paymentMethods(): List<Int> {
    return listOf(
        R.drawable.visa,
        R.drawable.americamexpo,
        R.drawable.mastercard,
        R.drawable.paypal,
        R.drawable.pay
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewPaymentScreen() {
    PaymentScreen(
        onClickBack = {},
        deliveryAddress = "123 Main St, Anytown, USA",
        deliveryTime = "10:00 AM - 11:00 AM",
        orderId = "123456",
        itemsTotal = 2,
        priceTotal = 100.0,
        deliveryFee = 10.0,
        onPayClick = {},
        onChangeAddressClick = {},
        onAddVoucherClick = {}

    )
}