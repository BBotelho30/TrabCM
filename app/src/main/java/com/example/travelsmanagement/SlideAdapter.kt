package com.example.travelsmanagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SlideAdapter(private val slides: List<SlideItem>) :
    RecyclerView.Adapter<SlideAdapter.SlideViewHolder>() {

    inner class SlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.imageSlide)
        val title: TextView = view.findViewById(R.id.titleSlide)
        val description: TextView = view.findViewById(R.id.descriptionSlide)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false)
        return SlideViewHolder(view)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val item = slides[position]
        holder.title.text = item.title
        holder.description.text = item.description
        holder.image.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int = slides.size
}
