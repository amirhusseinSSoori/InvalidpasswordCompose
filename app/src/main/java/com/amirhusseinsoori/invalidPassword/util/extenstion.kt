package com.amirhusseinsoori.invalidPassword.util

import androidx.compose.ui.graphics.Color
import com.amirhusseinsoori.invalidPassword.util.builderInvalidPass.InvalidPassword
import com.amirhusseinsoori.invalidPassword.ui.theme.green500
import com.amirhusseinsoori.invalidPassword.ui.theme.red500
import com.amirhusseinsoori.invalidPassword.ui.theme.red700
import com.amirhusseinsoori.invalidPassword.ui.theme.yellow500


data class ValueAndText(var value: Int, var text: String)
data class BrushAndColor(var color: Color, var brush: Float)
const val emptyString=" "
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
    object NoSpace: Pattern(Regex("(^\\S*\$)"))
    object Symbol: Pattern(Regex("^.*(?=.*[!@#$%^&*()-+=?<>]).*$"))
    object UpperCase: Pattern(Regex("(.*[A-Z].*)"))
    object DownCase: Pattern(Regex("(.*[a-z].*)"))
    object UseNumber: Pattern(Regex("(.*[0-9].*)"))
}
fun String.textPersian(): Boolean {
    for (i in 0 until Character.codePointCount(this, 0, this.length)) {
        val c = this.codePointAt(i)
        if (c in 0x0600..0x06FF || c == 0xFB8A || c == 0x067E || c == 0x0686 || c == 0x06AF) return true
    }
    return false
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

fun String.invalidPassword(): ValueAndText {
    var text = emptyString
    val value= InvalidPassword.Builder().inputValue(this).hasNumber()
        .hasUpperCase()
        .hasDownCase()
        .hasSymbol()
        .atLeast8()
        .hasPersian()
        .hasNoSpace()

    text = when {

        value.state == 2 -> {
            "very weak"
        }
        value.state == 3 -> {
            "weak"
        }
        value.state == 4 -> {
            "medium"
        }
        value.state >= 5 -> {
            "good"
        }
        else -> {
            emptyString
        }
    }
    return ValueAndText(value.state, text)
}
