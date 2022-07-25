package com.alpriest.lymmbeerfest.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.BeerFestTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

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
            modifier = Modifier .fillMaxSize(),
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
                            2 -> BrewlettePage().content(config)
                            3 -> BrewlettePage().content(config)
                            4 -> AboutPage().content(
                                onOpenUrl = { launchUrl(it) },
                                onSendEmail = { sendEmail(it) }
                            )
                        }
                    }
                }
            },
            bottomBar = {
                TabRow(
                    // Our selected tab is our current page
                    selectedTabIndex = pagerState.currentPage,
                    // Override the indicator, using the provided pagerTabIndicatorOffset modifier
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                        )
                    },
                    backgroundColor = Color.Black
                ) {
                    // Add tabs for all of our pages
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    modifier = Modifier.padding(0.dp),
                                    text = title
                                )
                            },
                            selected = pagerState.currentPage == index,
                            selectedContentColor = Gold,
                            unselectedContentColor = Color.White,
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