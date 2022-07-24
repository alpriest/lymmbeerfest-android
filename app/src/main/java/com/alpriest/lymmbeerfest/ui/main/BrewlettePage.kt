package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.viewinterop.AndroidView
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelView

class BrewlettePage {
    @Composable
    fun content(config: Config) {
        val wheelItems = ArrayList<WheelItem>()
        var isSpinning = remember { mutableStateOf(false) }
        var targetAngle = remember { mutableStateOf(0f) }
        var rotation: State<Float> = animateFloatAsState(
            targetValue = targetAngle.value,
            animationSpec = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing,
            )
        )

        for (brew in config.brews) {
            wheelItems.add(WheelItem(brew.androidColor(), brew.name))
        }

        Column {
            if (!isSpinning.value) {
                Text("Tap the wheel to spin")
            } else {
                Text("")
            }

            AndroidView(
                modifier = Modifier
                    .graphicsLayer { rotationZ = rotation.value }
                    .clickable {
                        isSpinning.value = true
                        targetAngle.value = 3600f
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
