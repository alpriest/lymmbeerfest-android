package com.alpriest.lymmbeerfest.ui.main.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.alpriest.lymmbeerfest.ui.main.Gin
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Subtitle
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.R
import com.alpriest.lymmbeerfest.ui.main.Brew
import com.alpriest.lymmbeerfest.ui.main.gold
import com.alpriest.lymmbeerfest.ui.main.models.GinConfigPage
import com.alpriest.lymmbeerfest.ui.main.models.Sponsor
import com.alpriest.lymmbeerfest.ui.main.models.Sponsors

class GinPage {
    @Composable
    fun content(config: Config) {
        val headings = config.gins.map { it.type }.distinct()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Title("Gin Palace")
            Header(config)

            headings.forEach { header ->
                GinGroupRow(header, config.gins.filter { it.type == header })
            }

            Text(
                "Thanks to our Gin sponsor:",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            SponsorView(config.sponsors.gin)
        }
    }

    @Composable
    private fun Header(config: Config) {
        Row(
            modifier = Modifier.padding(bottom = 28.dp)
        ) {
            AsyncImage(
                model = config.ginPage.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding()
                    .padding(end = 8.dp)
            )

            Text(config.ginPage.text)
        }
    }

    @Composable
    private fun GinGroupRow(heading: String, gins: List<Gin>) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Subtitle(heading)

            gins.forEach { gin ->
                GinRow(
                    modifier = Modifier.padding(bottom = 24.dp),
                    gin = gin
                )
            }
        }
    }

    @Composable
    private fun GinRow(modifier: Modifier = Modifier, gin: Gin) {
        Column(modifier = modifier) {
            Text(
                text = gin.distiller + ", " + gin.name + " (" + gin.abv + "%)",
                style = MaterialTheme.typography.h3
            )
            Text(gin.description)

            gin.sponsor?.let {
                Text(
                    "Sponsored by " + it,
                    color = MaterialTheme.colors.gold(),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
@Preview
fun GinGagePreview() {
    GinPage().content(Config.preview())
}

fun Config.Companion.preview(): Config {
    return Config(
        whenStr = "",
        howmuch = "",
        food = "",
        music = ArrayList(),
        brews = listOf(
            Brew("1",
            "Something",
            "BB Brewery",
            "A beer",
            "ABC Consulting",
            1.0,
            false,
            false,
            "#ff0000")
        ),
        gins = listOf(
            Gin(
                type = "Fruity Gins",
                distiller = "Bloom",
                name = "Passionfruit & Vanilla",
                description = "A combination of tangy passion fruit paired with creamy vanilla blossom.",
                abv = 40.0,
                sponsor = "ABC Consulting"
            )
        ),
        sponsors = Sponsors(
            main = Sponsor("", "", ""),
            food = Sponsor("", "", ""),
            gin = Sponsor("", "", "")
        ),
        ginPage = GinConfigPage(imageUrl = "", text = "")
    )
}
