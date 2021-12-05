package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faridwaid.doaku.R
import com.faridwaid.doaku.database.Note
import com.faridwaid.doaku.model.EditNoteViewModel

class EditNoteActivity : AppCompatActivity() {

    private lateinit var model : EditNoteViewModel
    private lateinit var note : LiveData<Note>

    companion object{
        const val NOTE_ID = "note_id"
        const val UPDATE_NOTE = "update_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        val id = intent.getStringExtra(NOTE_ID)
        val editNote: TextView = findViewById(R.id.etEditNote)
        model = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        note = id?.let { model.getNote(it) }!!
        note.observe(this, object : Observer<Note> {
            override fun onChanged(t: Note?) {
                editNote.setText(t?.note)
            }
        })
        val btnUpdate : Button = findViewById(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            val updateNote = editNote.text.toString()
            Intent().also {
                it.putExtra(NOTE_ID, id)
                it.putExtra(UPDATE_NOTE, updateNote)
                setResult(RESULT_OK, it)
                finish()
            }
        }
        val btnCancel : Button = findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }

    }
}