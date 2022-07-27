package com.alpriest.lymmbeerfest.ui.main.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.alpriest.lymmbeerfest.ui.main.Gin
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Subtitle
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.Title
import com.alpriest.lymmbeerfest.R

class GinPage {
    @Composable
    fun content(config: Config) {
        val headings = config.gins.map { it.type }.distinct()

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Title("Gin Palace")
            Header()
            headings.forEach { header ->
                GinGroupRow(header, config.gins.filter { it.type == header })
            }
        }
    }

    @Composable
    private fun Header() {
        Row(
            modifier = Modifier.padding(bottom = 28.dp)
        ) {
            Image(
                painterResource(id = R.drawable.gin_palace_logo),
                modifier = Modifier.padding(end = 8.dp),
                contentDescription = "Lymm & Tonic"
            )

            Text(stringResource(id = R.string.gin_palace_overview))
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
        }
    }
}

@Composable
@Preview
fun GinGagePreview() {
    Box() {
        Text("hello")
    }
//    val config = Config(whenStr = "", howmuch = "", food = "", music = ArrayList(), brews = ArrayList(), gins = ArrayList())
//    GinPage().content(config)
}