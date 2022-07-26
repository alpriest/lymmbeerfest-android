package com.alpriest.lymmbeerfest.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Subtitle

class GinPage {
    @Composable
    fun content(config: Config) {
        val headings = config.gins.map { it.type }.distinct()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            headings.forEach { header ->
                GinGroupRow(header, config.gins.filter { it.type == header })
            }
        }
    }

    private @Composable
    fun GinGroupRow(heading: String, gins: List<Gin>) {
        Column {
            Subtitle(heading)

            gins.forEach { gin ->
                GinRow(gin)
            }
        }
    }

    private @Composable
    fun GinRow(gin: Gin) {
        Text(gin.name)
        Text(gin.description)
    }
}

@Preview
@Composable
fun GinGagePreview() {
    val config = Config(whenStr = "", howmuch = "", food = "", music = ArrayList(), brews = ArrayList(), gins = ArrayList())
    GinPage().content(config)
}