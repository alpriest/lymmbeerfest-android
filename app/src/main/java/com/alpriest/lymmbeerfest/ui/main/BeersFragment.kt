package com.alpriest.lymmbeerfest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alpriest.lymmbeerfest.R

class BeersFragment(val config: Config) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.beer_fragment_item_list, container, false)
        val recycler: RecyclerView = view.findViewById(R.id.list)

        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyBeersRecyclerViewAdapter(config.brews)
        }
        return view
    }
}