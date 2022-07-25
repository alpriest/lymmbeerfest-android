package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
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
private val boldBodyText = Font(R.font.montserrat_bold, FontWeight.Bold)

private val tangerineFamily = FontFamily(curvy)
private val montserratFamily = FontFamily(bodyText, boldBodyText)
val Gold = Color(0xFFFFD700)

val beerfestTypography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    h2 = TextStyle(
        fontFamily = tangerineFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 38.sp,
        textAlign = TextAlign.Center
    ),
    h3 = TextStyle(
        fontFamily = montserratFamily,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    body1 = TextStyle(
        fontFamily = montserratFamily,
        color = Color.White,
        fontSize = 16.sp
    ),
    caption = TextStyle(
        fontFamily = montserratFamily,
        color = Gold,
        fontSize = 12.sp,
        textAlign = TextAlign.Right
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    content()
                }
            }
        }

        @Composable
        fun Title(text: String) {
            Text(
                text = text.uppercase(),
                style = MaterialTheme.typography.h1
            )
        }

        @Composable
        fun Subtitle(text: String) {
            Text(
                text = text,
                color = Gold,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(top = 28.dp)
            )
        }

        @Composable
        fun GoldButton(modifier: Modifier = Modifier, text: String) {
            Button(
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Gold,
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    color = Color.Black,
                    text = text
                )
            }
        }
    }
}