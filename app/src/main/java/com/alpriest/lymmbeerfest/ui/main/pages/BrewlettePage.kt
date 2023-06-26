package com.alpriest.lymmbeerfest.ui.main.pages

import android.util.DisplayMetrics
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelItem
import com.alpriest.lymmbeerfest.ui.main.LuckyWheel.WheelView
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.GoldButton
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.ui.main.models.Config

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
            wheelItems.add(WheelItem(brew.androidColor(), brew.name))
        }

        Box {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title(stringResource(R.string.beer_selector))

                Text(stringResource(R.string.beer_selector_overview))

                GoldButton(
                    modifier = Modifier.padding(top = 48.dp),
                    text = stringResource(R.string.find_me_a_drink),
                    enabled = !isSpinning.value
                ) {
                    isSpinning.value = true
                    displayBrewDetail.value = false
                    targetIndex.value = random.nextInt(config.brews.size)
                    targetAngle.value = (targetIndex.value.toFloat() * (360f / config.brews.size)) + targetAngle.value
                }

                if (displayBrewDetail.value) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 24.dp),
                        style = MaterialTheme.typography.h3,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        text = "Number " + config.brews[targetIndex.value].number + "\n" + config.brews[targetIndex.value].title()
                    )
                }
            }

            if (config.brews.isNotEmpty()) {
                AndroidView(
                    modifier = Modifier
                        .width((metrics.widthPixels * 2).dp)
                        .height((metrics.widthPixels * 2).dp)
                        .clipToBounds()
                        .offset(y = metrics.heightPixels.dp / 3.5f)
                        .scale(3.0f)
                        .graphicsLayer {
                            rotationZ = 0 - rotation.value - 90f - (360f / config.brews.size / 2.0f)
                        },
                    factory = { context ->
                        WheelView(context, attrs = null).apply {
                            addWheelItems(wheelItems)
                        }
                    }
                )
            }
        }
    }
}
