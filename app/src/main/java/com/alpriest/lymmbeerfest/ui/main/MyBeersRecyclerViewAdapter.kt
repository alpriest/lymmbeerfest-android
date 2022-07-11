package com.alpriest.lymmbeerfest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpriest.lymmbeerfest.databinding.BeerFragmentItemBinding

class MyBeersRecyclerViewAdapter(
    private val values: List<Brew>
) : RecyclerView.Adapter<MyBeersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BeerFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.numberView.text = item.number
        holder.titleView.text = item.brewery + ", " + item.name + " %.1f%%".format(item.abv)
        holder.descriptionView.text = item.description
        holder.sponsorView.text = "Sponsored by " + item.sponsor
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: BeerFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val numberView: TextView = binding.itemNumber
        val titleView: TextView = binding.title
        val descriptionView: TextView = binding.description
        val sponsorView: TextView = binding.sponsor
    }
}