package com.alpriest.lymmbeerfest.ui.main

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset
import java.util.*

class Event(
        val start: Date,
        val name: String
)

class Config(
    @SerializedName("when")
    val whenStr: String,
    val howmuch: String,
    val food: String,
    val music: List<Event>,
    val brews: List<Brew>
)

internal class ConfigLoader(private val assets: AssetManager) {
    fun load(): Config? {
        var json: String?
        try {
            val `is` = assets.open("config.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charset.defaultCharset())
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        val type = object : TypeToken<Config>() {}.type
        return Gson().fromJson<Config>(json, type)
    }
}
