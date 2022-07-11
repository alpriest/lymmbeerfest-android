package com.alpriest.lymmbeerfest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpriest.lymmbeerfest.databinding.GinFragmentItemBinding
import org.w3c.dom.Text

class MyGinsRecyclerViewAdapter(
    private val values: List<Gin>
) : RecyclerView.Adapter<MyGinsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            GinFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.distiller + ", " + item.name + " (%.1f%%".format(item.abv) + ")"
        holder.descriptionView.text = item.description
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: GinFragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleView: TextView = binding.title
        val descriptionView: TextView = binding.description
    }
}