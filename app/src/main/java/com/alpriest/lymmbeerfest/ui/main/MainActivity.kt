package com.alpriest.lymmbeerfest.ui.main

import android.content.Intent
import android.icu.text.CaseMap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.BeerFestTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ConfigLoader(getApplicationContext(), assets).load {
            it?.let {
                runOnUiThread {
                    setContent {
                        tabs(it)
                    }
                }
            }
        }
    }

    private fun launchUrl(url: Uri) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = url
        startActivity(openURL)
    }

    private fun sendEmail(address: String) {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", address, null
            )
        )
        startActivity(Intent.createChooser(intent, "Choose an Email client:"))
    }

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun tabs(config: Config) {
        val metrics = application.resources.displayMetrics
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        val titles = listOf(
            "Home",
            "Beers",
            "Brewlette",
            "Gin",
            "About"
        )

        return Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                BeerFestTheme {
                    HorizontalPager(
                        modifier = Modifier.padding(all = 12.dp),
                        count = titles.size,
                        state = pagerState
                    ) { page ->
                        when (page) {
                            0 -> HomePage().content(config)
                            1 -> BeersPage().content(config)
                            2 -> BrewlettePage().content(config, metrics)
                            3 -> BrewlettePage().content(config, metrics)
                            4 -> AboutPage().content(
                                onOpenUrl = { launchUrl(it) },
                                onSendEmail = { sendEmail(it) }
                            )
                        }
                    }
                }
            },
            topBar = {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    backgroundColor = Color.Black,
                    edgePadding = 0.dp,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier
                                .pagerTabIndicatorOffset(pagerState, tabPositions),
                            color = MaterialTheme.colors.gold(),
                        )
                    }
                ) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    modifier = Modifier.padding(0.dp),
                                    text = title
                                )
                            },
                            selected = pagerState.currentPage == index,
                            selectedContentColor = MaterialTheme.colors.gold(),
                            unselectedContentColor = Color.Gray,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.scrollToPage(
                                        index
                                    )
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}

data class TabItem(val title: String, val icon: String)