package com.castres.breand.block6.p1.androidproject


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PartnershipsAdapter (private val partnershipsList: List<Partnerships>):
    RecyclerView.Adapter<PartnershipsAdapter.PartnershipsViewHolder>(){


    class PartnershipsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val psImageView : ImageView = itemView.findViewById(R.id.psImageView)
        val psNameTv : TextView =itemView.findViewById(R.id.psItem1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartnershipsViewHolder {
        val view3 = LayoutInflater.from(parent.context).inflate(R.layout.partnerships, parent, false)
        return PartnershipsViewHolder(view3)
    }

    override fun getItemCount(): Int {
        return partnershipsList.size
    }

    override fun onBindViewHolder(holder: PartnershipsViewHolder, position: Int) {
        val item = partnershipsList[position]
        holder.psImageView.setImageResource(item.psImage)
        holder.psNameTv.text=item.psName
    }

}