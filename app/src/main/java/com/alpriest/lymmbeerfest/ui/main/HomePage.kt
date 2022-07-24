package com.alpriest.lymmbeerfest.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.subtitle
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.title
import java.text.SimpleDateFormat
import java.util.*

class HomePage {
    @Composable
    fun content(config: Config) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            title("Lymm Beer Festival")

            subtitle("When")
            Text(
                text = config.whenStr,
                textAlign = TextAlign.Center
            )

            subtitle("How Much")
            Text(
                text = config.howmuch,
                textAlign = TextAlign.Center
            )

            subtitle(text = "Where")
            Text(
                text = stringResource(R.string.address),
                textAlign = TextAlign.Center
            )
            MapImage()

            subtitle("What else")
            Text(
                text = config.food,
                textAlign = TextAlign.Center
            )

            subtitle("Music")
            Text(
                text = music(config.music),
                textAlign = TextAlign.Center
            )

            Text("",
            modifier = Modifier.padding(top = 24.dp))
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    private @Composable
    fun MapImage() {
        val painter =
            rememberImagePainter(data = "https://maps.googleapis.com/maps/api/staticmap?center=" + 53.381098 + "," + -2.476424 + "&zoom=15&size=300x300&sensor=false&key=AIzaSyBEUMHrUP7tf28iVD9eljaheu_l-BZYnbg")

        Image(
            painter = painter,
            contentDescription = "Map of Lymm",
            modifier = Modifier.size(300.dp, 300.dp).padding(top = 12.dp),
            contentScale = ContentScale.Crop
        )
    }

    private fun music(music: List<Event>): String = music
        .sortedBy { it.start }
        .map { formattedDateTime(it.start) + " - " + it.name }
        .joinToString(separator = "\n")

    @SuppressLint("SimpleDateFormat")
    private fun formattedDateTime(date: Date): String {
        val pattern = "EEEE HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }
}