package com.example.partsharing.Recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.partsharing.R
import com.example.partsharing.ResponseBody.UrlModelReport

class CustomRecyclerAdapter(private val names: List<UrlModelReport>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var FIOtv: TextView? = null
        var Destv: TextView? = null
        var Lesstv: TextView? = null
        var Cabtv: TextView? = null
        var Buildtv: TextView? = null

        init {
            FIOtv = itemView.findViewById(R.id.txt_FIO)
            Destv = itemView.findViewById(R.id.txt_Des)
            Lesstv = itemView.findViewById(R.id.txt_Less)
            Cabtv = itemView.findViewById(R.id.txt_Cab)
            Buildtv = itemView.findViewById(R.id.txt_Build)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.FIOtv?.text = "${names[position].userLastName} ${names[position].userFirstName}"
        holder.Destv?.text = names[position].description.toString()
        holder.Lesstv?.text = "${names[position].lessonNumber} пара"
        holder.Cabtv?.text = "${names[position].cabinet} кабинет"
        holder.Buildtv?.text = names[position].building.toString()
    }

    override fun getItemCount(): Int {
        return  names.size
    }
}