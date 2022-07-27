package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
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
        fontSize = 12.sp,
        textAlign = TextAlign.Right
    )
)

val beerfestColors = lightColors(
    primary = Color.White,
)

fun Colors.gold(): Color {
    return Color(0xFFFFD700)
}

class StyleGuide {
    companion object {
        @Composable
        fun BeerFestTheme(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
            MaterialTheme(colors = beerfestColors, typography = beerfestTypography) {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ) {
                    Image(
                        painterResource(id = R.drawable.lymm_beer_fest_home_bg),
                        contentDescription = "",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .alpha(0.15f)
                            .matchParentSize()
                    )
                    content()
                }
            }
        }

        @Composable
        fun Title(text: String) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text.uppercase(),
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.padding(bottom = 28.dp)
                )
            }
        }

        @Composable
        fun Subtitle(text: String) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = text,
                    color = MaterialTheme.colors.gold(),
                    style = MaterialTheme.typography.h2
                )
            }
        }

        @Composable
        fun GoldButton(
            modifier: Modifier = Modifier,
            text: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) {
            Button(
                modifier = modifier,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.gold(),
                ),
                onClick = onClick
            ) {
                Text(
                    color = Color.Black,
                    text = text
                )
            }
        }
    }
}