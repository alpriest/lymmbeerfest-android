package com.alpriest.lymmbeerfest.ui.main

import android.content.Context
import android.content.res.AssetManager
import com.google.android.gms.tasks.Tasks
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import java.nio.channels.Channels
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

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

internal class ConfigLoader(private val context: Context, private val assets: AssetManager) {
    fun load(onLoad: (Config?) -> Unit) {
        loadFromRemote(context) {
            val text: String? = it ?: loadFromAssets()
            text?.let {
                val type = object : TypeToken<Config>() {}.type
                onLoad(Gson().fromJson<Config>(text, type))
            } ?: onLoad(null)
        }
    }

    private fun loadFromLocal(context: Context, onLoad: (String?) -> Unit) {
        val configFile = File(context.cacheDir, "config").toString() + ".json"
        val json = FileInputStream(configFile).bufferedReader().use { it.readText() }

        onLoad(json)
    }

    private fun loadFromRemote(context: Context, onLoad: (String?) -> Unit) {
        val url = URL("https://www.lymmbeerfest.co.uk/app/config.json")
        val configFile = File(context.cacheDir, "config").toString() + ".json"

        GlobalScope.launch(Dispatchers.IO) {
            url.openStream().use {
                Channels.newChannel(it).use { rbc ->
                    FileOutputStream(configFile).use { fos ->
                        fos.channel.transferFrom(rbc, 0, Long.MAX_VALUE)
                    }

                    loadFromLocal(context, onLoad)
                }
            }
        }
    }

    private fun loadFromAssets(): String? {
        val json: String?
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

        return json
    }
}
