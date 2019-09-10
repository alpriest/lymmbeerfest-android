package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.alpriest.lymmbeerfest.R
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val config = ConfigLoader(assets).load()
        config?.let {
            val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, config)
            val viewPager = findViewById<ViewPager>(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs = findViewById<TabLayout>(R.id.tabs)
            tabs.setupWithViewPager(viewPager)
        }
    }
}