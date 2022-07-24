package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
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
            "Beers"
        )

        return Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {
                BeerFestTheme {
                    HorizontalPager(count = 2, state = pagerState) { page ->
                        when (page) {
                            1 -> HomePage().content(config)
                            0 -> BrewsPage().content(config)
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
                                    pagerState.animateScrollToPage(
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
//
//    @Preview
//    @Composable
//    fun PreviewHome() {
//        home(
//            Config(
//                whenStr = "Friday 15th July 2022 : 6pm - 11pm\nSaturday 16th July 2022 : Noon - 11pm",
//                howmuch = "£5 door entry (includes glass)\n£2 per token\n1 token for a half pint of beer/cider\n2 tokens for a single gin (4 for a double)\nSoft Drinks are FREE",
//                food = "",
//                music = ArrayList(),
//                brews = ArrayList(),
//                gins = ArrayList()
//            )
//        )
//    }
}
