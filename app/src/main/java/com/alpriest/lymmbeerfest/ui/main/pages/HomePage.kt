package com.alpriest.lymmbeerfest.ui.main.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.Event
import com.alpriest.lymmbeerfest.ui.main.StyleGuide
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Subtitle
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.alpriest.lymmbeerfest.ui.main.models.Sponsor
import com.alpriest.lymmbeerfest.ui.main.models.Sponsors
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun HomepagePreview() {
    val config = Config(
        whenStr = "",
        howmuch = "",
        food = "",
        music = ArrayList(),
        brews = ArrayList(),
        gins = ArrayList(),
        sponsors = Sponsors(
            main = Sponsor("Aviation Solutions", "https://lymmbeerfest.co.uk/app/airspace-aviation-solutions.png", ""),
            food = Sponsor("", "", ""),
            gin = Sponsor("", "", "")
        )
    )

    return StyleGuide.BeerFestTheme {
        HomePage().content(config, { })
    }
}

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

            Subtitle("Thanks to our main sponsor")

            SponsorView(
                config.sponsors.main,
                modifier = Modifier.padding(bottom = 44.dp)
            )
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

@Composable
private fun SponsorLogo(sponsor: Sponsor, modifier: Modifier = Modifier) {
    AsyncImage(
        model = sponsor.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .fillMaxWidth()
            .padding()
    )
}

@Composable
fun SponsorView(sponsor: Sponsor, modifier: Modifier = Modifier) {
    val openUrlLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result if needed
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(bottom = 24.dp)
            .clickable {
                openUrlLauncher.launch(Intent(Intent.ACTION_VIEW, Uri.parse(sponsor.url)))
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SponsorLogo(sponsor = sponsor)
    }
}
