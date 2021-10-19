package com.example.mysettingapp.ui

import androidx.compose.ui.graphics.Color
import com.example.mysettingapp.ui.theme.green500
import com.example.mysettingapp.ui.theme.red500
import com.example.mysettingapp.ui.theme.red700
import com.example.mysettingapp.ui.theme.yellow500


data class ValueAndText(var value: Int, var text: String)
data class BrushAndColor(var color: Color, var brush: Float)

fun Int.coverButton():Float {
    return when {
        this == 1 -> {
            0.3F
        }
        this == 2 -> {
            0.5F
        }
        this == 3 -> {
            0.7F
        }
        this == 4 -> {
            0.9F
        }
        this >= 5 -> {
            1F
        }
        else -> {
            0F
        }
    }
}

sealed class Pattern(val rex:Regex) {
    object NoSpace:Pattern(Regex("(^\\S*\$)"))
    object Symbol:Pattern(Regex("^.*(?=.*[!@#$%^&*()-+=?<>]).*$"))
    object UpperCase:Pattern(Regex("(.*[A-Z].*)"))
    object DownCase:Pattern(Regex("(.*[a-z].*)"))
    object UseNumber:Pattern(Regex("(.*[0-9].*)"))

}
fun Int.chooseColor(
    item1: Boolean = true,
    item2: Boolean = true,
    item3: Boolean = true,
    item4: Boolean = true,
    item5: Boolean = true
): BrushAndColor {
    return if (this == 1 && item1) {
        BrushAndColor(Color.Red, 1F)
    } else if (this == 2 && item2) {
        BrushAndColor(red700, 1F)
    } else if (this == 3 && item3) {
        BrushAndColor(red500, 1F)
    } else if (this == 4 && item4) {
        BrushAndColor(yellow500, 1F)
    } else if (this >= 5 && item5) {
        BrushAndColor(green500, 1F)
    } else {
        BrushAndColor(Color.White, 0.0F)
    }
}