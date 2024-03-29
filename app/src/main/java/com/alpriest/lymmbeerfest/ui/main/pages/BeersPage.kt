package com.alpriest.lymmbeerfest.ui.main.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpriest.lymmbeerfest.ui.main.Brew
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.ui.main.gold
import com.alpriest.lymmbeerfest.ui.main.models.GinConfigPage
import com.alpriest.lymmbeerfest.ui.main.models.Sponsor
import com.alpriest.lymmbeerfest.ui.main.models.Sponsors
import kotlin.collections.ArrayList

class BeersPage {
    @Composable
    fun content(config: Config) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Title("Beers")

            config.brews.forEach { brew ->
                BrewRow(brew)
            }
        }
    }

    @Composable
    fun BrewRow(brew: Brew) {
        val uriHandler = LocalUriHandler.current

        Row(modifier = Modifier.padding(top = 24.dp)) {
            Column(Modifier.width(24.dp)) {
                Text(
                    text = brew.number,
                    style = MaterialTheme.typography.h3
                )
            }

            Column {
                Text(
                    text = brew.title(),
                    style = MaterialTheme.typography.h3
                )
                Text(brew.description)

                if (brew.sponsor.isNotEmpty()) {
                    Text(
                        "Sponsored by " + brew.sponsor,
                        color = MaterialTheme.colors.gold(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                brew.sponsorUrl?.let { url ->
                                    uriHandler.openUri(url)
                                }
                            }
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun ComposablePreview() {
        return BeersPage().content(Config.preview())
    }
}
