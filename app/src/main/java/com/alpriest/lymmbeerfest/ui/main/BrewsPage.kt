package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import java.util.ArrayList

class BrewsPage {
    @Composable
    fun content(config: Config) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Title("Beers")

            Column {
                config.brews.forEachIndexed { index, brew ->
                    BrewRow(index, brew)
                }
            }
        }
    }

    @Composable
    fun BrewRow(index: Int, brew: Brew) {
        Row {
            Column(Modifier.width(24.dp)) {
                Text(
                    text = index.toString(),
                    style = MaterialTheme.typography.h3
                )
            }

            Column {
                Text(
                    text = brew.name,
                    style = MaterialTheme.typography.h3
                )
                Text(brew.description)
            }
        }
    }

    @Preview
    @Composable
    fun ComposablePreview() {
        val config = Config(
            whenStr = "",
            howmuch = "",
            food = "",
            music = ArrayList(),
            brews = ArrayList(),
            gins = ArrayList()
        )

        return BrewsPage().content(config)
    }
}
