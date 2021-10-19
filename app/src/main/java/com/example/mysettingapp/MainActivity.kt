package com.example.mysettingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.mysettingapp.ui.*

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
                PasswordItemView(invalidPassword(passwordState))
                invalidPassword(passwordState).value.apply {
                    SubmitButton(
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

    if (str.matches(Pattern.UseNumber.rex)) value += 1
    if (str.matches(Pattern.UpperCase.rex)) value += 1
    if (str.matches(Pattern.DownCase.rex)) value += 1
    if (str.matches(Pattern.Symbol.rex)) value += 1
    if (!(str.matches(Pattern.NoSpace.rex))) value *= 0
    if (str.length > 7) value += 1
    if (textPersian(str)) value *= 0

    text = when {
        value == 2 -> {
            "very weak"
        }
        value == 3 -> {
            "weak"
        }
        value == 4 -> {
            "medium"
        }
        value >= 5 -> {
            "good"
        }
        else -> {
            ""
        }
    }
    return ValueAndText(value, text)
}

 fun textPersian(s: String): Boolean {
    for (i in 0 until Character.codePointCount(s, 0, s.length)) {
        val c = s.codePointAt(i)
        if (c in 0x0600..0x06FF || c == 0xFB8A || c == 0x067E || c == 0x0686 || c == 0x06AF) return true
    }
    return false
}

