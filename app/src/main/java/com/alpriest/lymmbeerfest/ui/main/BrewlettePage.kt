package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelView

class BrewlettePage {
    @Composable
    fun content(config: Config) {
        val wheelItems = ArrayList<WheelItem>()
        val isSpinning = remember { mutableStateOf(false) }
        val targetAngle = remember { mutableStateOf(0f) }
        val random = java.util.Random()
        val rotation: State<Float> = animateFloatAsState(
            targetValue = targetAngle.value,
            animationSpec = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing,
            )
        )

        for (brew in config.brews) {
            wheelItems.add(WheelItem(brew.androidColor(), brew.name))
        }

        CompositionLocalProvider(
            LocalRippleTheme provides ClearRippleTheme
        ) {
            Box {
                if (!isSpinning.value) {
                    Text("Tap the wheel to spin")
                } else {
                    Text("")
                }

                // Doesn't always stop on an item. Need to update targetAngle maths
                AndroidView(
                    modifier = Modifier
                        .scale(2.5f)
                        .offset(y = 170.dp)
                        .graphicsLayer { rotationZ = rotation.value }
                        .clickable {
                            isSpinning.value = true
                            targetAngle.value = targetAngle.value + ((random.nextInt(config.brews.size).toFloat() * (360f / config.brews.size) * 3))
                        },
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