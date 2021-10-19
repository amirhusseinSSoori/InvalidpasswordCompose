package com.example.mysettingapp

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mysettingapp.ui.*
import com.example.mysettingapp.ui.theme.Purple500

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
                WrongFiledView(invalidPassword(passwordState))
                invalidPassword(passwordState).value.apply {
                    ButtonBrush(
                        coverButton(), chooseColor().color
                    )
                }

            }
        }
    }
}


fun invalidPassword(str: String): ValueAndText {
    var value = 0
    var text = " "

    if (str.matches(Regex("(.*[0-9].*)"))) value += 1
    if (str.matches(Regex("(.*[A-Z].*)"))) value += 1
    if (str.matches(Regex("(.*[a-z].*)"))) value += 1
    if (str.matches(Regex("^.*(?=.*[!@#$%^&*()-+=?<>]).*$"))) value += 1
    if (str.length > 7) value += 1
    if (textPersian(str)) value *= 0
    if (!(str.matches(Regex("(^\\S*\$)")))) value *= 0

    text = if (value in 1..2) {
        "very weak"
    } else if (value == 3) {
        "weak"
    } else if (value == 4) {
        "medium"
    } else if (value >= 5) {
        "good"
    } else {
        ""
    }
    return ValueAndText(value, text)
}

private fun textPersian(s: String): Boolean {
    for (i in 0 until Character.codePointCount(s, 0, s.length)) {
        val c = s.codePointAt(i)
        if (c in 0x0600..0x06FF || c == 0xFB8A || c == 0x067E || c == 0x0686 || c == 0x06AF) return true
    }
    return false
}

