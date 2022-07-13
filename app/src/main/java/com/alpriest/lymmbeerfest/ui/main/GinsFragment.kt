package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alpriest.lymmbeerfest.R

class GinsFragment(val config: Config) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gins, container, false)

        val headings = config.gins.map { it.type }.distinct()
        val gin_list: LinearLayout = view.findViewById(R.id.gin_type_list)

        context?.let { ctx ->
            headings.forEach { header ->
                val headerView = TextView(ctx)
                with(headerView) {
                    text = header
                    setTextColor(ContextCompat.getColor(ctx, R.color.gold))
                    typeface = ResourcesCompat.getFont(ctx, R.font.tangerine_bold)
                    textSize = 32f
                    gravity = Gravity.CENTER
                    setPadding(0,0,0,16)
                }

                val recycler = RecyclerView(ctx)
                recycler.isNestedScrollingEnabled = false
                recycler.layoutManager = LinearLayoutManager(context)
                recycler.adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == header })

                gin_list.addView(headerView)
                gin_list.addView(recycler)
            }
        }

        return view
    }
}

class Gin(var type: String, var distiller: String, var name: String, var description: String, var abv: Double) {
}