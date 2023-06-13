package com.alpriest.lymmbeerfest.ui.main.models

import com.alpriest.lymmbeerfest.ui.main.Brew
import com.alpriest.lymmbeerfest.ui.main.Event
import com.alpriest.lymmbeerfest.ui.main.Gin
import com.google.gson.annotations.SerializedName

class Config(
    @SerializedName("when")
    val whenStr: String,
    val howmuch: String,
    val food: String,
    val music: List<Event>,
    val brews: List<Brew>,
    val gins: List<Gin>,
    val sponsors: Sponsors
)

class Sponsors(
    val main: Sponsor,
    val food: Sponsor,
    val gin: Sponsor
)