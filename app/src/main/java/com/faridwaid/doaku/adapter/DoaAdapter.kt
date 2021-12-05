package com.faridwaid.doaku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.activity.CreateDoaFavorite
import com.faridwaid.doaku.activity.DetailDoaActivity
import com.faridwaid.doaku.activity.FavoriteActivity
import com.faridwaid.doaku.model.DoaResponse

class DoaAdapter(private val list: ArrayList<DoaResponse>): RecyclerView.Adapter<DoaAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvResponse: TextView = itemView.findViewById(R.id.tv_doa_name)
        val mContext = itemView.context
        var favoritkan: ImageView = itemView.findViewById(R.id.favoritkan)
        fun bind(doa: DoaResponse){
            with(itemView){
                val text = "${doa.doa}"
                tvResponse.text = text
                favoritkan.setOnClickListener {
                    val moveToFavorite = Intent(mContext, CreateDoaFavorite::class.java)
                    moveToFavorite.putExtra(CreateDoaFavorite.EXTRA_ID, doa.id)
                    moveToFavorite.putExtra(CreateDoaFavorite.EXTRA_DOA, doa.doa)
                    moveToFavorite.putExtra(CreateDoaFavorite.EXTRA_AYAT, doa.ayat)
                    moveToFavorite.putExtra(CreateDoaFavorite.EXTRA_LATIN, doa.latin)
                    moveToFavorite.putExtra(CreateDoaFavorite.EXTRA_ARTINYA, doa.artinya)
                    mContext.startActivity(moveToFavorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_doa, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])

        val dataDoa = list[position]

        val mContext = holder.itemView.context

        holder.itemView.setOnClickListener{
            val moveDetail = Intent(mContext, DetailDoaActivity::class.java)
            moveDetail.putExtra(DetailDoaActivity.EXTRA_ID, dataDoa.id)
            moveDetail.putExtra(DetailDoaActivity.EXTRA_DOA, dataDoa.doa)
            moveDetail.putExtra(DetailDoaActivity.EXTRA_AYAT, dataDoa.ayat)
            moveDetail.putExtra(DetailDoaActivity.EXTRA_LATIN, dataDoa.latin)
            moveDetail.putExtra(DetailDoaActivity.EXTRA_ARTINYA, dataDoa.artinya)
            mContext.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = list.size
}