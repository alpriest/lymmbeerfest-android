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
            Gin("fruity", "Bloom", "Passionfruit & Vanilla", "A combination of tangy passion fruit paired with creamy vanilla blossom.", 40.0),
            Gin("fruity", "Grenalls", "Blueberry", "Sweet but slightly tart with a subtle hint of floral.", 40.0),
            Gin("fruity", "Kuro", "Natsu Peach Gin", "Fruity & sweet, with a robust gin core.", 40.0),
            Gin("fruity", "Lymm Gin", "Blood Orange", "A refreshing summer drink to revive your senses.", 40.0),
            Gin("fruity", "Lymm Gin", "Raspberry", "A palate of raspberry at the front with Juniper and spices at the back", 40.0),
            Gin("fruity", "The Lakes Gin", "Pink Grapefruit Gin", "Zesty pink grapefruit vigour. ", 46.0),
            Gin("fruity", "Three Wrens", "Exquisite Citrus Gin", "Delicious exotic aroma with a dry refreshing taste.", 40.0),
            Gin("fruity", "Three Wrens", "Key Lime Pie", "Lemon thyme & dandelion with fresh lime zest.", 40.0),
            Gin("fruity", "Three Wrens", "Rhubarb Gin", "Distilled with juniper, cassia, cinnamon, sweet orange peel and fresh ginger.", 40.0),
            Gin("fruity", "Zymurgorium", "Sloe Gin", "A spicy Sloe Gin with a rich flavour of spice & plum.", 40.0),

            Gin("classic", "Capesthorne Cheshire", "Dry Gin", "Classic gin packed full of flavour.", 40.0),
            Gin("classic", "Kuro", "Alpine Dry Gin", "Japan-inspired gin crafted in Cheshire. ", 43.0),
            Gin("classic", "Lymm Gin", "London Dry Gin", "Packed with botanicals to tantalise your taste buds.", 40.0),
            Gin("classic", "The Lakes Gin", "Classic English Gin", "A mix of over ten carefully selected botanicals native to the Lake District National Park. ", 46.0),
            Gin("classic", "Three Wrens", "Legends Strength", "Legends strength is only for the brave!", 57.0),
            Gin("classic", "Three Wrens", "Original Dry Gin", "Juniper is the heart and soul of this gin.", 42.0),

            Gin("floral_herby", "Bloom", "Jasmine & Rose", "Inspired by summer days spent in the garden,", 40.0),
            Gin("floral_herby", "Kuro", "Haru Blossom", "A pink gin flavoured with fleeting springtime cherry blossoms.", 40.0),
            Gin("floral_herby", "Manchester Gin", "Wild Spirit", "A savoury gin with complex herbaceous notes.", 40.0),
            Gin("floral_herby", "Meadows Gin (Urmston)", "Classic", "One sip inspires the essence of the meadow.", 42.0),
            Gin("floral_herby", "Ophir", "Oriental Spiced London Dry", "Unique spiced gin – some like it hot!", 40.0),
            Gin("floral_herby", "Three Wrens", "Bison Grass Gin", "Unique gin – first of its kind. Summer hay, lemongrass & vanilla", 41.5),
            Gin("floral_herby", "Three Wrens", "Lemon Verbena & Rose Gin", "Delightful floral tones with a herbaceous finish.", 42.0),

            Gin("liqueurs", "Cheshire Grins", "Espresso Martini", "A unique cocktail gin experience", 20.0),
            Gin("liqueurs", "Lakes Gins", "Elderflower", "Refreshingly floral and vibrant. ", 25.0),
            Gin("liqueurs", "No name (Manchester)", "Dandelion and Burdock", "A sophisticated liqueur brings new life into a beloved childhood classic.", 18.0),
            Gin("liqueurs", "Riverside", "Cucumber", "Delicately aromatic – ultimately refreshing.", 20.0),
            Gin("liqueurs", "Riverside", "Passionfruit", "A summer liqueur infused with fresh passionfruit.", 20.0),
            Gin("liqueurs", "Zymurgorium", "Flamingo Electric Blue & Scottish Raspberry", "Lay back and let the Flamingo house band rock your taste buds!", 20.0),
            Gin("liqueurs", "Zymurgorium", "Pink Pornstar Martini", "Notes of light berry & pure passion fruit juice.", 20.0),

            Gin("alcohol_free", "Clean Co", "Classic Gin", "Aromatic botanical ingredients create the finished non-alcoholic spirit.", 0.0),
            Gin("alcohol_free", "Clean Co", "Rhubarb Gin", "Notes of rhubarb, juniper, & citrus with a hint of coriander, mint & cinnamon.", 0.0),
            Gin("alcohol_free", "Seedlip", "Non-alcoholic Gin", "What to drink when you’re not drinking.", 0.0)
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
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "classic" })
        }

        val floral_herby_recycler: RecyclerView = view.findViewById(R.id.floral_herby)
        with(floral_herby_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "floral_herby" })
        }

        val liquers_recycler: RecyclerView = view.findViewById(R.id.liquers)
        with(liquers_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "liqueurs" })
        }

        val alcohol_free_recycler: RecyclerView = view.findViewById(R.id.alcohol_free)
        with(alcohol_free_recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyGinsRecyclerViewAdapter(data.filter { it.type == "alcohol_free" })
        }

        return view
    }
}

class Gin(var type: String, var distiller: String, var name: String, var description: String, var abv: Double) {
}