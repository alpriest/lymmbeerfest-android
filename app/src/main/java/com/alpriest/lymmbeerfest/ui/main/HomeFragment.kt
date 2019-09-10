package com.alpriest.lymmbeerfest.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.alpriest.lymmbeerfest.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment(val config: Config) : Fragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.home, container, false)

        val mapView: ImageView = fragment.findViewById(R.id.mapView)

        val url = Uri.parse("https://maps.googleapis.com/maps/api/staticmap?center=" + 53.381098 + "," + -2.476424 + "&zoom=15&size=300x300&sensor=false&key=AIzaSyBEUMHrUP7tf28iVD9eljaheu_l-BZYnbg")
        Picasso.get().load(url).into(mapView)

        fragment.findViewById<View>(R.id.directions).setOnClickListener {
            val url = String.format(Locale.ENGLISH, "https://www.google.com/maps/dir/?api=1&destination=%f,%f", 53.381098, -2.476424)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        
        bind(config, fragment)

        return fragment
    }

    @SuppressLint("SetTextI18n")
    private fun bind(config: Config, fragment: View) {
        fragment.findViewById<TextView>(R.id.whenText).setText(config.whenStr)
        fragment.findViewById<TextView>(R.id.howMuchText).setText(config.howmuch)
        fragment.findViewById<TextView>(R.id.whatElseText).setText(config.food + "\n\n" + music(config))
    }

    private fun music(config: Config): String {
        return config.music
                .sortedBy { it.start }
                .map { formattedDateTime(it.start) + " - " + it.name }
                .joinToString(separator = "\n")
    }

    private fun formattedDateTime(date: Date): String {
        val pattern = "EEEE HH:mm"
        val simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(date)
    }
}
