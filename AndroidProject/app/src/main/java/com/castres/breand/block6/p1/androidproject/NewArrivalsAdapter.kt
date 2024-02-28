package com.castres.breand.block6.p1.androidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewArrivalsAdapter (private val newArrivalsList : List<NewArrivals>) :
    RecyclerView.Adapter<NewArrivalsAdapter.NewArrivalsHolder>() {

    class NewArrivalsHolder(itemVIew: View) : RecyclerView.ViewHolder(itemVIew) {
        val naImageView: ImageView = itemView.findViewById(R.id.naImageView)
        val naNameTv: TextView = itemView.findViewById(R.id.naItem1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.newarrivals, parent, false)
        return NewArrivalsHolder(view)
    }

    override fun onBindViewHolder(holder: NewArrivalsHolder, position: Int) {
        val item = newArrivalsList[position]
        holder.naImageView.setImageResource(item.naImage)
        holder.naNameTv.text=item.naName

    }
    override fun getItemCount(): Int {
        return newArrivalsList.size


    }


}