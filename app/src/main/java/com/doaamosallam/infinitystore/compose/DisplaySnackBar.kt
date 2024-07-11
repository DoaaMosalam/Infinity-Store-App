package com.doaamosallam.infinitystore.compose

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DisplaySnackBar(
    message: String,
    dismiss: String
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        LaunchedEffect(Unit) {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = message,
                    actionLabel = dismiss
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        // Handle action click if needed
                    }

                    SnackbarResult.Dismissed -> {
                        // Handle dismiss if needed
                    }
                }
            }
        }
    }
}