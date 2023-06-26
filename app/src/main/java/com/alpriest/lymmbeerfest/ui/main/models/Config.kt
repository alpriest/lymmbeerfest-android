package com.alpriest.lymmbeerfest.ui.main.models

import com.alpriest.lymmbeerfest.ui.main.Brew
import com.alpriest.lymmbeerfest.ui.main.Event
import com.alpriest.lymmbeerfest.ui.main.Gin
import com.alpriest.lymmbeerfest.ui.main.pages.GinPage
import com.google.gson.annotations.SerializedName
import java.net.URL

class Config(
    @SerializedName("when")
    val whenStr: String,
    val howmuch: String,
    val food: String,
    val music: List<Event>,
    val brews: List<Brew>,
    val gins: List<Gin>,
    val sponsors: Sponsors,
    val ginPage: GinConfigPage
) {
    companion object {}
}

class Sponsors(
    val main: Sponsor,
    val food: Sponsor,
    val gin: Sponsor
)

class GinConfigPage(
    val imageUrl: String,
    val text: String
)