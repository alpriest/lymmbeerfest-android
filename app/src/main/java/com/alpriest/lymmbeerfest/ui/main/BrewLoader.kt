package com.alpriest.lymmbeerfest.ui.main

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.alpriest.lymmbeerfest.ui.main.models.Config
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Streaming
import java.io.*
import java.net.ConnectException
import java.net.URL
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.util.*

class Event(
    val start: Date,
    val name: String
)

internal class ConfigLoader(private val context: Context, private val assets: AssetManager) {
    private val TAG = "ConfigLoader"

    fun load(onLoad: (Config?) -> Unit) {
        loadFromRemote(context) {
            val text: String? = it ?: loadFromAssets()

            text?.let {
                val type = object : TypeToken<Config>() {}.type
                onLoad(Gson().fromJson<Config>(text, type))
            } ?: onLoad(null)
        }
    }

    private fun loadFromLocalCache(context: Context, onLoad: (String?) -> Unit) {
        val configFile = File(context.cacheDir, "config.json")
        try {
            val json = FileInputStream(configFile).bufferedReader().use { it.readText() }

            onLoad(json)
        } catch (ex: FileNotFoundException) {
            Log.i(TAG, "loadFromRemote: Failed to load cached config")
            onLoad(null)
        }
    }

    private fun loadFromRemote(context: Context, onLoad: (String?) -> Unit) {
        val configFile = File(context.cacheDir, "config.json").toString()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.lymmbeerfest.co.uk")
            .build()

        val service = retrofit.create(LymmBeerFestService::class.java)

        GlobalScope.launch {
            try {
                val response = service.loadConfig().execute()
                response.body()?.byteStream()?.use {
                    FileOutputStream(configFile).use { targetOutputStream ->
                        it.copyTo(targetOutputStream)
                    }
                }

                loadFromLocalCache(context, onLoad)
            } catch (ex: ConnectException) {
                Log.i(TAG, "loadFromRemote: Failed to load remote config")
                loadFromLocalCache(context, onLoad)
            } catch (ex: UnknownHostException) {
                Log.i(TAG, "loadFromRemote: Failed to load remote config")
                loadFromLocalCache(context, onLoad)
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

interface LymmBeerFestService {
    @GET("/app/config.json")
    @Streaming
    fun loadConfig(): Call<ResponseBody>
}