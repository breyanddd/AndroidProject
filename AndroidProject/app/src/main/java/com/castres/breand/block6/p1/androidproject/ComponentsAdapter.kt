package com.castres.breand.block6.p1.androidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ComponentsAdapter (private val componentsList: List<Components>):
    RecyclerView.Adapter <ComponentsAdapter.ComponentsViewHolder>(){

    class ComponentsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val componentsImageView : ImageView = itemView.findViewById(R.id.cImageView)
        val componentsNameTv : TextView =itemView.findViewById(R.id.cItem1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentsViewHolder {
        val view2 = LayoutInflater.from(parent.context).inflate(R.layout.components, parent, false)
        return ComponentsViewHolder(view2)
    }

    override fun getItemCount(): Int {
        return componentsList.size
    }

    override fun onBindViewHolder(holder: ComponentsViewHolder, position: Int) {
        val item = componentsList[position]
        holder.componentsImageView.setImageResource(item.componentsImage)
        holder.componentsNameTv.text=item.componentsName
    }
}