package com.amirhusseinsoori.invalidPassword.util

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amirhusseinsoori.invalidpassword.R

@Composable
fun TextField(passwordState:String, onTextChange: (String) -> Unit) {
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


@Composable
fun PasswordItemView(value: Pair<Int, String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 22.dp)
    ) {
        value.first.apply {
            PasswordItem(chooseColor())
            PasswordItem(chooseColor(item1 = false))
            PasswordItem(chooseColor(item1 = false, item2 = false))
            PasswordItem(chooseColor(item1 = false, item2 = false, item3 = false))
            PasswordItem(
                chooseColor(
                    item1 = false,
                    item2 = false,
                    item3 = false,
                    item4 = false
                )
            )
            Text(
            text = value.second,
            color = chooseColor().first,
            modifier = Modifier.width(190.dp),
            textAlign = TextAlign.End)
    }
    }
}

@Composable
fun PasswordItem(bC: Pair<Color,Float>) {
    Canvas(
        modifier = Modifier
            .height(20.dp)
            .width(30.dp)
            .padding(top = 8.dp, start = 8.dp)
            .border(BorderStroke(1.dp, Color(android.R.color.transparent))),
    ) {
        drawRect(
            SolidColor(bC.first),
            size = Size(
                bC.second * size.width,
                size.height
            ),
        )
    }
}

@Composable
fun SubmitButton(brush: Float, c: Color) {
    Canvas(
        modifier = Modifier
            .height(70.dp)
            .width(120.dp)
            .padding(top = 25.dp)
            .border(
                width = 1.dp,
                Color(android.R.color.transparent), shape = RoundedCornerShape(20.dp)
            )
    ) {
        drawRoundRect(
            SolidColor(c),
            size = Size(
                brush * size.width,
                size.height
            ),
            alpha = brush,
            cornerRadius = CornerRadius(
                x = 20.dp.toPx(),
                y = 20.dp.toPx()
            )
        )
        val paint = Paint().apply {
            textAlign = Paint.Align.CENTER
            textSize = 50f
            color = Color.White.toArgb()
        }
        drawContext.canvas.nativeCanvas.drawText(
            "Submit",
            size.width / 2,
            size.height / 1.5f,
            paint
        )
    }

}
