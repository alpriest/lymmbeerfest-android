package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alpriest.lymmbeerfest.R

private val curvy = Font(R.font.tangerine_bold, FontWeight.Bold)
private val bodyText = Font(R.font.montserrat_regular)

private val fontFamily = FontFamily(bodyText, curvy)

val beerfestTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        textAlign = TextAlign.Center
    ),
    body1 = TextStyle(
        fontFamily = fontFamily,
        color = Color.White,
        fontSize = 16.sp
    )
)

val beerfestColors = lightColors(
    primary = Color.White,
    surface = Color.Black,
    onSurface = Color.Red,
    secondary = Color.Green,
    primaryVariant = Color.Blue
)

@Composable
fun BeerFestTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = beerfestColors, typography = beerfestTypography) {
        Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
            content()
        }
    }
}