package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alpriest.lymmbeerfest.R

class GinsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = arrayOf<Gin>(
            Gin("fruity", "The Lakes Gin", "Pink Grapefruit Gin"),
            Gin("fruity", "Three Wrens", "Exquisite Citrus Gin"),
            Gin("fruity", "Grenalls", "Blueberry"),
            Gin("fruity", "Lymm Gin", "Raspberry"),
            Gin("fruity", "Lymm Gin", "Blood Orange"),
            Gin("fruity", "Three Wrens", "Rhubarb Gin"),
            Gin("fruity", "Bloom", "Passionfruit & Vanilla"),
            Gin("fruity", "Zymurgorium", "Sloe Gin"),
            Gin("fruity", "Three Wrens", "Key Lime Pie"),

            Gin("traditional", "The Lakes Gin", "Classic English Gin"),
            Gin("traditional", "Three Wrens", "Original Dry Gin"),
            Gin("traditional", "Lymm Gin", "London Dry Gin"),
            Gin("traditional", "Three Wrens", "Legend’s Strength (57%)"),

            Gin("floral_herby", "Three Wrens", "Lemon Verbena & Rose Gin"),
            Gin("floral_herby", "Bloom", "Jasmine & Rose"),
            Gin("floral_herby", "Three Wrens", "Bison Grass Gin"),
            Gin("floral_herby", "Meadows Gin (Urmston)", "Classic"),
            Gin("floral_herby", "Manchester Gin", "Wild Spirit"),

            Gin("liquers", "No name (Manchester)", "Dandelion and Burdock"),
            Gin("liquers", "Zymurgorium", "Flamingo Electric Blue & Scottish Raspberry"),
            Gin("liquers", "Zymurgorium", "Pink Pornstar Martini"),
            Gin("liquers", "Riverside", "Cucumber"),
            Gin("liquers", "Riverside", "Passionfruit"),
            Gin("liquers", "Cheshire Grins", "Espresso Martini"),

            Gin("alcohol_free", "Clean Co", "Rhubarb Gin"),
            Gin("alcohol_free", "Gordons", "Gin"),
        )

        val view = inflater.inflate(R.layout.fragment_gins, container, false)

        val fruity_recycler: RecyclerView = view.findViewById(R.id.fruity)
        with(fruity_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "fruity" })
        }

        val traditional_recycler: RecyclerView = view.findViewById(R.id.traditional)
        with(traditional_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "traditional" })
        }

        val floral_herby_recycler: RecyclerView = view.findViewById(R.id.floral_herby)
        with(floral_herby_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "floral_herby" })
        }

        val liquers_recycler: RecyclerView = view.findViewById(R.id.liquers)
        with(liquers_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "liquers" })
        }

        val alcohol_free_recycler: RecyclerView = view.findViewById(R.id.alcohol_free)
        with(alcohol_free_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "alcohol_free" })
        }

        return view
    }
}

class Gin(var type: String, var distiller: String, var name: String) {
}