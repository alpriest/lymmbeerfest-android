package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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
private val boldBodyText = Font(R.font.montserrat_bold, FontWeight.Bold)

private val tangerineFamily = FontFamily(curvy)
private val montserratFamily = FontFamily(bodyText, boldBodyText)

val beerfestTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        fontFamily = tangerineFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        textAlign = TextAlign.Center
    ),
    body1 = TextStyle(
        fontFamily = montserratFamily,
        color = Color.White,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = montserratFamily,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
)

val beerfestColors = lightColors(
    primary = Color.White,
)

class StyleGuide {
    companion object {
        @Composable
        fun BeerFestTheme(content: @Composable () -> Unit) {
            MaterialTheme(colors = beerfestColors, typography = beerfestTypography) {
                Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                    content()
                }
            }
        }

        @Composable
        fun Title(text: String) {
            Text(
                text = text,
                style = MaterialTheme.typography.h1
            )
        }

        @Composable
        fun Subtitle(text: String) {
            Text(
                text = text,
                color = colorResource(R.color.gold),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 28.dp)
            )
        }
    }
}