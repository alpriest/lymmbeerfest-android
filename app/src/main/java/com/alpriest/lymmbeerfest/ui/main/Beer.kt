package com.alpriest.lymmbeerfest.ui.main

import android.graphics.Color

class Brew(
    var number: String,
    var name: String,
    var brewery: String,
    var description: String,
    var sponsor: String,
    var abv: Double,
    private val colour: String
) {
    fun androidColor(): Int {
        return Color.parseColor("#$colour")
    }
}
