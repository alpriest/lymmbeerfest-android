package com.alpriest.lymmbeerfest.ui.main

import android.graphics.Color

class Brew(
    var number: String,
    var name: String,
    var brewery: String,
    var description: String,
    var sponsor: String,
    var abv: Double,
    var glutenFree: Boolean,
    var veganFriendly: Boolean,
    private val colour: String
) {
    fun androidColor(): Int {
        return Color.parseColor("#$colour")
    }

    fun title(): String {
        return brewery + ", " + name + " (" + abv + "%)" + (if (glutenFree) " GF" else "") + (if (veganFriendly) " VF" else "")
    }
}
