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
import com.faridwaid.doaku.model.NoteViewModel

class DetailNoteActivity : AppCompatActivity() {

    private lateinit var model : EditNoteViewModel
    private lateinit var note : LiveData<Note>
    private lateinit var model2: NoteViewModel
    private var title: String = "Informasi Detail Catatan"

    companion object{
        const val NOTE_ID = "note_id"
        const val NOTE_TITLE = "note_title"
        const val NOTE = "note"
        const val ID_NOTE = "id_note"
        const val UPDATE_TITLE = "update_title"
        const val UPDATE_NOTE = "update_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        val actionBar = supportActionBar
        actionBar!!.title = title

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true)

        //mendapatkan id untuk set data
        val id = intent.getStringExtra(DetailNoteActivity.NOTE_ID)

        val setTitle: TextView = findViewById(R.id.detail_title_note)
        val setNote: TextView = findViewById(R.id.detail_note)

        //set data dari id yang telah didapatkan
        model = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        note = id?.let { model.getNote(it) }!!
        note.observe(this, object : Observer<Note> {
            override fun onChanged(t: Note?) {
                setTitle.setText(t?.title)
                setNote.setText(t?.note)
            }
        })

        //update ketika button update diklik, cancel ketika button cancel diklik
        val btnUpdate : Button = findViewById(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            val updateTitle = setTitle.text.toString()
            val updateNote = setNote.text.toString()
            Intent().also {
                it.putExtra(DetailNoteActivity.ID_NOTE, id)
                it.putExtra(DetailNoteActivity.UPDATE_TITLE, updateTitle)
                it.putExtra(DetailNoteActivity.UPDATE_NOTE, updateNote)
                setResult(RESULT_OK, it)
                finish()
            }
        }
        val btnCancel : Button = findViewById(R.id.btnCancel)
        btnCancel.setOnClickListener {
            finish()
        }
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}