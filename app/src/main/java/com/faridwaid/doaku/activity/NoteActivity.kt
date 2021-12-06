package com.faridwaid.doaku.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.adapter.NoteAdapter
import com.faridwaid.doaku.database.Note
import com.faridwaid.doaku.model.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteActivity : AppCompatActivity() {

    companion object{
        const val CREATE_NOTE_REQUEST_CODE = 1
        const val DETAIL_NOTE_REQUEST_CODE = 2
        const val UPDATE_NOTE_REQUEST_CODE = 3
    }

    private lateinit var model: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        adapter = NoteAdapter()
        model = ViewModelProvider(this).get(NoteViewModel::class.java)
        showListNotes()

        val btnCreate: FloatingActionButton = findViewById(R.id.button)
        btnCreate.setOnClickListener{
            Intent(this@NoteActivity, CreateNoteActivity::class.java).also {
                startActivityForResult(it, CREATE_NOTE_REQUEST_CODE)
            }
        }

    }

    private fun showListNotes() {
        var rvNote : RecyclerView = findViewById(R.id.rvNote)
        rvNote.setHasFixedSize(true)
        rvNote.layoutManager = LinearLayoutManager(this)
        model.getListNotes().observe(this, object : Observer<List<Note>> {
            override fun onChanged(t: List<Note>?) {
                if (t != null) {
                    adapter.setListNote(t)
                }
                rvNote.adapter = adapter
            }

        })
        adapter.setOnItemClickCallback(object : NoteAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Note) {
                Intent(this@NoteActivity, DetailNoteActivity::class.java).also {
                    it.putExtra(DetailNoteActivity.NOTE_ID, data.id)
                    it.putExtra(DetailNoteActivity.NOTE_TITLE, data.title)
                    it.putExtra(DetailNoteActivity.NOTE, data.note)
                    startActivityForResult(it, UPDATE_NOTE_REQUEST_CODE)
                }


            }

        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteDialog(adapter.getNoteAt(viewHolder.layoutPosition))
                showListNotes()
            }
        }).attachToRecyclerView(rvNote)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            val note = data?.getStringExtra(CreateNoteActivity.NEW_NOTE)!!
            val title = data?.getStringExtra(CreateNoteActivity.NEW_TITLE)!!
            if (note != null && title!= null) {
                model.insert(title, note)
            }
            Toast.makeText(this, "Note Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show()
        } else if (requestCode == UPDATE_NOTE_REQUEST_CODE && resultCode == RESULT_OK){
            val noteId = data?.getStringExtra(DetailNoteActivity.ID_NOTE)!!
            val updatedTitle = data?.getStringExtra(DetailNoteActivity.UPDATE_TITLE)!!
            val updatedNote = data?.getStringExtra(DetailNoteActivity.UPDATE_NOTE)!!
            val note = Note(
                noteId,
                updatedTitle,
                updatedNote
            )
            model.update(note)
            Toast.makeText(this, "Note Berhasil Diupdate!", Toast.LENGTH_SHORT).show()
            showListNotes()
        }
    }

    private fun deleteDialog(note: Note){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin hapus ${note.note}?")
            setNegativeButton("Batal", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            setPositiveButton("Hapus", DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
                model.delete(note)
            })
        }
        alertDialog.show()
    }

}