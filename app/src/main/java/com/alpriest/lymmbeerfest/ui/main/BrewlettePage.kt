package com.alpriest.lymmbeerfest.ui.main

import android.util.DisplayMetrics
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelView

class BrewlettePage {
    @Composable
    fun content(config: Config, metrics: DisplayMetrics) {
        val wheelItems = ArrayList<WheelItem>()
        val isSpinning = remember { mutableStateOf(false) }
        val targetIndex = remember { mutableStateOf(1) }
        val targetAngle = remember { mutableStateOf(0f) }
        val displayBrewDetail = remember { mutableStateOf(false) }
        val random = java.util.Random()
        val rotation: State<Float> = animateFloatAsState(
            targetValue = targetAngle.value,
            animationSpec = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing,
            ),
            finishedListener = {
                isSpinning.value = false
                displayBrewDetail.value = true
            }
        )

        for (brew in config.brews) {
            wheelItems.add(WheelItem(brew.androidColor(), brew.name + brew.number))
        }

        CompositionLocalProvider(
            LocalRippleTheme provides ClearRippleTheme
        ) {
            Box {
                Column {
                    if (!isSpinning.value) {
                        Text("Tap the wheel to spin")
                    } else {
                        Text("")
                    }

                    if (displayBrewDetail.value) {
                        Text(config.brews[targetIndex.value].name)
                    }

                    Text(targetIndex.value.toString())
                    Text(metrics.heightPixels.dp.toString())
                }

                // Needs to reset angle before next spin (or take into account current angle when calculating new angle
                AndroidView(
                    modifier = Modifier
                        .width((metrics.widthPixels * 2).dp)
                        .offset(y = metrics.heightPixels.dp / 4)
                        .scale(3.5f)
                        .graphicsLayer { rotationZ = 0 -rotation.value - 90f - (360f / config.brews.size / 2.0f) }
                        .clickable {
                            isSpinning.value = true
                            displayBrewDetail.value = false
                            targetIndex.value = random.nextInt(config.brews.size)
                            targetAngle.value = targetIndex.value.toFloat() * (360f / config.brews.size)
                        }
                        .clipToBounds(),
                    factory = { context ->
                        WheelView(context, attrs = null)
                    },
                    update = {
                        it.addWheelItems(wheelItems)
                    }
                )
            }
        }
    }
}

object ClearRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Transparent

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0.0f,
        focusedAlpha = 0.0f,
        hoveredAlpha = 0.0f,
        pressedAlpha = 0.0f,
    )
}