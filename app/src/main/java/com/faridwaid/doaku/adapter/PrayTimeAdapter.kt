package com.faridwaid.doaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.model.PrayTimeResponse
import com.faridwaid.doaku.model.Times


class PrayTimeAdapter(private val list: ArrayList<Times>): RecyclerView.Adapter<PrayTimeAdapter.PrayTimeViewHolder>() {

    inner class PrayTimeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvSubuh : TextView = itemView.findViewById(R.id.tv_doa_name)
        fun bind(prayResponse: Times){
            with(itemView){
                val text: String = "userId: ${prayResponse.Isha}"
                tvSubuh.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)
        return PrayTimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrayTimeViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}