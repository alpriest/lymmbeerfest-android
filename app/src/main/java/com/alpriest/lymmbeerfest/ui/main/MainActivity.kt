package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun tabs(config: Config) {
        val pagerState = rememberPagerState()
        val coroutineScope = rememberCoroutineScope()
        val titles = listOf(
            "Home",
            "Beers",
            "Brewlette"
        )

        return Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                BeerFestTheme {
                    HorizontalPager(count = 3, state = pagerState) { page ->
                        when (page) {
                            0 -> HomePage().content(config)
                            1 -> BrewsPage().content(config)
                            2 -> BrewlettePage().content(config)
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
                            text = { Text(title, color = Color.White) },
                            selected = pagerState.currentPage == index,
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
