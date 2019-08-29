package com.alpriest.lymmbeerfest.ui.main.LuckyWheel

import android.graphics.Color

class WheelItem(var color: Int, var text: String) {
    fun textColor(): Int {

        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)

        val luminance = ((0.299 * red + 0.587 * green + 0.114 * blue) / 255.0) * 1000

        return if (luminance > 500) {
            Color.BLACK
        } else {
            Color.WHITE
        }
    }
}