package com.example.mysettingapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mysettingapp.R

@Composable
fun MyTextField(passwordState:String,onTextChange: (String) -> Unit) {
//    var passwordState by remember {
//        mutableStateOf("")
//    }

    var isVisible by remember {
        mutableStateOf(false)
    }
    val icon = if (isVisible)
        painterResource(id = R.drawable.ic_visibility)
    else
        painterResource(id = R.drawable.ic_visibility_off)

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .absolutePadding(left = 30.dp, top = 50.dp,right = 30.dp),
        value = passwordState,
        onValueChange = {
            onTextChange(it)
        },
        placeholder = {
            Text(text = "Password")
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isVisible = !isVisible
                }) {
                Icon(painter = icon, contentDescription = "null")
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation =
        if (isVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}