package com.alpriest.lymmbeerfest.ui.main.pages

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.alpriest.lymmbeerfest.ui.main.Event
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Subtitle
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.ui.main.models.Sponsor
import java.text.SimpleDateFormat
import java.util.*

class HomePage {
    @Composable
    fun content(config: Config, onOpenUrl: (Uri) -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Title("Lymm Beer Festival")

            TitledParagraph(title = "When", text = config.whenStr)
            TitledParagraph(title = "How Much", text = config.howmuch)
            TitledParagraph(title = "Where", text = stringResource(R.string.address))
            Map(onOpenUrl)
            TitledParagraph(title = "What else", text = config.food)
            TitledParagraph("Music", text = music(config.music))

            Subtitle("Thanks to...")

            Sponsors(config.sponsors)
        }
    }

    @Composable
    private fun TitledParagraph(title: String, text: String) {
        Subtitle(title)
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 28.dp)
        )
    }

    @Composable
    private fun Map(onOpenUrl: (Uri) -> Unit) {
        MapImage(
            modifier = Modifier
                .clickable {
                    onOpenUrl(
                        Uri.parse(
                            String.format(
                                Locale.ENGLISH,
                                "https://www.google.com/maps/dir/?api=1&destination=%f,%f",
                                53.381098,
                                -2.476424
                            )
                        )
                    )
                }
                .padding(bottom = 28.dp)
        )
    }

    @Composable
    private fun Sponsors(sponsors: List<Sponsor>) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sponsors.forEach { sponsor ->
                Card(
                    modifier = Modifier
                        .widthIn(min = 300.dp)
                        .padding(bottom = 24.dp),
                    shape = RoundedCornerShape(size = 5.dp),
                    backgroundColor = Color.DarkGray,
                    elevation = 24.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Sponsor(sponsor = sponsor)

                        Text(
                            text = sponsor.name,
                            style = MaterialTheme.typography.h3,
                        )

                        Text(sponsor.description)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    private fun Sponsor(sponsor: Sponsor) {
        val painter = rememberImagePainter(data = sponsor.imageUrl)

        Image(
            painter = painter,
            contentDescription = "Sponsor logo",
            modifier = Modifier
                .height(150.dp)
                .padding(12.dp)
        )
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    private fun MapImage(modifier: Modifier = Modifier) {
        val painter =
            rememberImagePainter(data = "https://maps.googleapis.com/maps/api/staticmap?center=" + 53.381098 + "," + -2.476424 + "&zoom=15&size=300x300&sensor=false&key=AIzaSyBEUMHrUP7tf28iVD9eljaheu_l-BZYnbg")

        Image(
            painter = painter,
            contentDescription = "Map of Lymm",
            modifier = modifier
                .size(300.dp, 300.dp)
                .padding(top = 12.dp),
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