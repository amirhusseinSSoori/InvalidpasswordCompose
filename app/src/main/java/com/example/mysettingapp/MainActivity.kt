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
import com.example.mysettingapp.ui.MyTextField
import com.example.mysettingapp.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val (passwordState, passwordStateOnChange) = remember { mutableStateOf("") }
            Column(       verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                MyTextField(passwordState,passwordStateOnChange)
                CustomView(invalidPassword(passwordState))
                ButtonBrush(setButton(invalidPassword(passwordState).value),chooseColor(invalidPassword(passwordState).value,check1 = true,check2 = true,check3 = true,check4 = true,check5 = true).color)
            }
        }
    }
}
@Composable
fun CustomView(value:ValueAndText) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 22.dp)) {
        WrongFiled(chooseColor(value.value,check1 = true,check2 = true,check3 = true,check4 = true,check5 = true))
        WrongFiled(chooseColor(value.value,check1 = false,check2 = true,check3 = true,check4 = true,check5 = true))
        WrongFiled(chooseColor(value.value,check1 = false,check2 = false,check3 = true,check4 = true,check5 = true))
        WrongFiled(chooseColor(value.value,check1 = false,check2 = false,check3 = false,check4 = true,check5 = true))
        WrongFiled(chooseColor(value.value,check1 = false,check2 = false,check3 = false,check4 = false,check5 = true))
        Text(text = value.text,color = chooseColor(value.value,check1 = true,check2 = true,check3 = true,check4 = true,check5 = true).color,modifier = Modifier.width(190.dp),textAlign = TextAlign.End) }
}
@Composable
fun ButtonBrush(brush:Float, c:Color){
    Canvas(modifier = Modifier
        .height(50.dp)
        .width(120.dp)
        .padding(top = 8.dp, start = 0.dp)
        .border(
            width = 1.dp,
            Purple500, shape = RoundedCornerShape(20.dp)
        )
    ) {
        drawRoundRect(
                SolidColor(c),
                size =  Size(
                    brush * size.width,
                    size.height),
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
            size.width/2,
            size.height/1.5f,
            paint
        )
    }

    }
fun setButton(value:Int):Float{
    return if(value == 1){
        0.3F
    }else if( value == 2){
        0.5F
    }else if( value == 3 ){
        0.7F
    }else if( value == 4 ){
        0.9F
    } else if( value >= 5 ){
        1F
    } else{
        0F
    }
}

data class ValueAndText(var value:Int,var text:String)
data class BrushAndColor(var color:Color,var brush:Float,)

fun chooseColor(value:Int,check1:Boolean,check2:Boolean,check3:Boolean,check4: Boolean,check5: Boolean) : BrushAndColor{
    return if(value == 1 && check1){
        BrushAndColor( Color.Red,1F)
    }else if( value == 2 && check2){
        BrushAndColor(Color(0xFFF44336),1F)
    }else if( value == 3 && check3){
        BrushAndColor(Color(0xFFF44336),1F)
    }else if( value == 4 && check4){
        BrushAndColor(Color(0xFFFF9800),1F)
    } else if( value >= 5 && check5){
        BrushAndColor(Color(0xFF4CAF50),1F)
    } else{
        BrushAndColor(Color.White,0.0F)
    }
}



@Composable
fun WrongFiled(bC:BrushAndColor){
    Canvas(modifier = Modifier
        .height(20.dp)
        .width(30.dp)
        .padding(top = 8.dp, start = 8.dp)
        .border(BorderStroke(1.dp, Purple500)),



    ) {
        drawRect(
            SolidColor(bC.color),
            size =  Size(
                bC.brush * size.width,
                size.height
            ),
            )
    }
}


fun invalidPassword(str:String):ValueAndText{
    var value = 0
    var text = " "
    if(str.matches(Regex("(.*[0-9].*)"))){
        value += 1
    }
    if(str.matches(Regex("(.*[A-Z].*)"))){
        value += 1
    }
    if(str.matches(Regex("(.*[a-z].*)"))){
        value += 1
    }
    if ((str!!.matches(Regex("^.*(?=.*[!@#$%^&*()-+=?<>]).*$")))) {
        value += 1
    }
    if(str.length > 7){
        value += 1
    }
    if(textPersian(str)){
        value *= 0
    }
    if (!(str.matches(Regex("(^\\S*\$)")))){
        value *= 0
    }

    text = if(value in 1..2){
        "very weak"
    } else if (value ==3){
        "weak"
    }else if(value == 4){
        "medium"
    }else if(value >=5){
        "good"
    }else {
        ""
    }
    return ValueAndText(value,text)

}




private fun textPersian(s: String): Boolean {
    for (i in 0 until Character.codePointCount(s, 0, s.length)) {
        val c = s.codePointAt(i)
        if (c in 0x0600..0x06FF || c == 0xFB8A || c == 0x067E || c == 0x0686 || c == 0x06AF) return true
    }
    return false
}

