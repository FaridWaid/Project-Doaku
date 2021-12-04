package com.faridwaid.doaku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.database.Favorite

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private lateinit var list: List<Favorite>
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvDoaName : TextView = itemView.findViewById(R.id.tv_doa_name)
        fun bind(favorite: Favorite){
            with(itemView){
                tvDoaName.text = favorite.doa
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(favorite) }
            }
        }
    }

    fun setListNote(list: List<Favorite>){
        this.list = list
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Favorite = list.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Favorite)
    }
}