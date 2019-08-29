package com.alpriest.lymmbeerfest.ui.main

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

internal class BrewLoader(private val activity: Activity) {

    fun load(): List<Brew>? {
        var json: String?
        try {
            val `is` = activity.assets.open("brews.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        val type = object : TypeToken<List<Brew>>() {}.type
        return Gson().fromJson<List<Brew>>(json, type)
    }
}
