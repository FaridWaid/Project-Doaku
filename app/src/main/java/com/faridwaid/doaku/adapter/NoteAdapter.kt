package com.faridwaid.doaku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.database.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var list: List<Note>
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tv_title_note)
        val hapusNote : ImageView = itemView.findViewById(R.id.hapus)
        fun bind(note: Note){
            with(itemView){
                tvTitle.text = note.title
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(note) }
            }
            hapusNote.setOnClickListener {
                onItemClickCallback?.onDeleteItem(note)
            }
        }
    }

    fun setListNote(list: List<Note>){
        this.list = list
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note = list.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Note)
        fun onDeleteItem(data: Note)
    }

}