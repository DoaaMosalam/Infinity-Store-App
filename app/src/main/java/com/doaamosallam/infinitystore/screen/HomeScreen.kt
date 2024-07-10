package com.doaamosallam.infinitystore.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.doaamosallam.infinitystore.compose.Header
import com.doaamosallam.infinitystore.compose.Text

//state hoisting
@Composable
fun HomeScreen(

){

}
// home screen
@Composable
fun HomeContainer(){
   Column {
    Header(title = "Home Screen", subtitle ="Products" )


   }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){
    HomeContainer()

}
