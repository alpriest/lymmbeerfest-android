package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alpriest.lymmbeerfest.ui.main.StyleGuide.Companion.BeerFestTheme

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
            HomePage().content(config)
        }
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
