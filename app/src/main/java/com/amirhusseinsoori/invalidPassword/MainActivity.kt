package com.amirhusseinsoori.invalidPassword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.amirhusseinsoori.invalidPassword.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (passwordState, passwordStateOnChange) = remember { mutableStateOf("") }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(passwordState, passwordStateOnChange)
                PasswordItemView(passwordState.invalidPassword())
                passwordState.invalidPassword().first.apply {
                    SubmitButton(
                        coverButton(), chooseColor().first
                    )
                }
            }
        }
    }
}





