package com.alpriest.lymmbeerfest.ui.main

import android.graphics.Color

class Brew(
    val number: String,
    val name: String,
    val brewery: String,
    val description: String,
    val sponsor: String,
    val abv: Double,
    val glutenFree: Boolean,
    val veganFriendly: Boolean,
    val sponsorUrl: String?,
    val colour: String
) {
    fun androidColor(): Int {
        return Color.parseColor("#$colour")
    }

    fun title(): String {
        return brewery + ", " + name + " (" + abv + "%)" + (if (glutenFree) " GF" else "") + (if (veganFriendly) " VF" else "")
    }
}
