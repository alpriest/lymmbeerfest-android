package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.alpriest.lymmbeerfest.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ConfigLoader(getApplicationContext(), assets).load {
            it?.let {
                runOnUiThread {
                    setContent {
                        home(it)
                    }
//                    val sectionsPagerAdapter =
//                        SectionsPagerAdapter(this, supportFragmentManager, it)
//                    val viewPager = findViewById<ViewPager>(R.id.view_pager)
//                    viewPager.adapter = sectionsPagerAdapter
//                    val tabs = findViewById<TabLayout>(R.id.tabs)
//                    tabs.setupWithViewPager(viewPager)
//
//                    tabs.setTabWidthAsWrapContent(0)
//                    tabs.setTabWidthAsWrapContent(2)
//                    tabs.setTabWidthAsWrapContent(4)
                }
            }
        }
    }

    @Composable
    fun home(config: Config) {
        BeerFestTheme {
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
            }
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
            modifier = Modifier.size(300.dp, 300.dp),
            contentScale = ContentScale.Crop
        )
    }

    private fun music(music: List<Event>): String {
        return music
            .sortedBy { it.start }
            .map { formattedDateTime(it.start) + " - " + it.name }
            .joinToString(separator = "\n")
    }

    private fun formattedDateTime(date: Date): String {
        val pattern = "EEEE HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }

    @Composable
    fun title(text: String) {
        Text(
            text = text,
            style = typography.h1
        )
    }

    @Composable
    fun subtitle(text: String) {
        Text(
            text = text,
            color = colorResource(R.color.gold),
            style = typography.h2,
            modifier = Modifier.padding(top = 28.dp)
        )
    }

    @Preview
    @Composable
    fun PreviewHome() {
        home(
            Config(
                whenStr = "Friday 15th July 2022 : 6pm - 11pm\nSaturday 16th July 2022 : Noon - 11pm",
                howmuch = "£5 door entry (includes glass)\n£2 per token\n1 token for a half pint of beer/cider\n2 tokens for a single gin (4 for a double)\nSoft Drinks are FREE",
                food = "",
                music = ArrayList(),
                brews = ArrayList(),
                gins = ArrayList()
            )
        )
    }
}

//fun TabLayout.setTabWidthAsWrapContent(tabPosition: Int) {
//    val layout = (this.getChildAt(0) as LinearLayout).getChildAt(tabPosition) as LinearLayout
//    val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
//    layoutParams.weight = 0f
//    layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
//    layout.layoutParams = layoutParams
//}
