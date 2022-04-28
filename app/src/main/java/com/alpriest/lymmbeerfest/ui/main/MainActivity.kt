package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.alpriest.lymmbeerfest.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = ConfigLoader(getApplicationContext(), assets).load {
            it?.let {
                runOnUiThread {
                    val sectionsPagerAdapter =
                        SectionsPagerAdapter(this, supportFragmentManager, it)
                    val viewPager = findViewById<ViewPager>(R.id.view_pager)
                    viewPager.adapter = sectionsPagerAdapter
                    val tabs = findViewById<TabLayout>(R.id.tabs)
                    tabs.setupWithViewPager(viewPager)
                }
            }
        }
    }
}