package com.alpriest.lymmbeerfest.ui.main

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Typeface
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
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

//        val recycler: RecyclerView = view.findViewById(R.id.gin_type_list)
//        with(recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = GinTypeListRecyclerViewAdapter(config.gins)
//        }

        val gin_list: LinearLayout = view.findViewById(R.id.gin_type_list)

        context?.let { ctx ->
            headings.forEach { header ->
                val headerView = TextView(ctx)
                with(headerView) {
                    text = header
                    setTextColor(ctx.getColor(R.color.gold))
                    typeface = ResourcesCompat.getFont(ctx, R.font.tangerine_bold)
                    textSize = 32f
                    gravity = Gravity.CENTER
                }

                val recycler = RecyclerView(ctx)
                recycler.isNestedScrollingEnabled = false
//                recycler.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

//                android:layout_width="match_parent"
//                android:layout_height="match_parent"
//                android:layout_marginLeft="16dp"
//                android:layout_marginRight="16dp"
//                app:layoutManager="LinearLayoutManager"
//                android:name="com.alpriest.lymmbeerfest.ui.main.GinsFragment"

                recycler.layoutManager = LinearLayoutManager(context)
                recycler.adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == header })

                gin_list.addView(headerView)
                gin_list.addView(recycler)
            }
        }
//
//        val fruity_recycler: RecyclerView = view.findViewById(R.id.fruity)
//        with(fruity_recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == "fruity" })
//        }
//
//        val traditional_recycler: RecyclerView = view.findViewById(R.id.traditional)
//        with(traditional_recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == "classic" })
//        }
//
//        val floral_herby_recycler: RecyclerView = view.findViewById(R.id.floral_herby)
//        with(floral_herby_recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == "floral_herby" })
//        }
//
//        val liquers_recycler: RecyclerView = view.findViewById(R.id.liquers)
//        with(liquers_recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == "liqueurs" })
//        }
//
//        val alcohol_free_recycler: RecyclerView = view.findViewById(R.id.alcohol_free)
//        with(alcohol_free_recycler) {
//            layoutManager = LinearLayoutManager(context)
//            adapter = MyGinsRecyclerViewAdapter(config.gins.filter { it.type == "alcohol_free" })
//        }

        return view
    }
}

class Gin(var type: String, var distiller: String, var name: String, var description: String, var abv: Double) {
}