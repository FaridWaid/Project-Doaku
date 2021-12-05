package com.faridwaid.doaku.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.faridwaid.doaku.database.Note
import com.faridwaid.doaku.database.NoteDao
import com.faridwaid.doaku.database.NoteRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteDao: NoteDao?
    private var noteDB: NoteRoomDatabase?
    private var list: LiveData<List<Note>>

    init {
        noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB?.noteDao()
        list = noteDao?.getListNotes()!!
    }

    fun insert(textNote: String){
        insertToDatabase(textNote)
    }

    fun getListNotes(): LiveData<List<Note>> = list

    private fun insertToDatabase(textNote: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val noteId = UUID.randomUUID().toString()
            val note = Note(
                noteId,
                textNote
            )
            noteDao?.insert(note)
        }
    }

    fun update(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao?.updateNote(note)
        }
    }

    fun delete(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            noteDao?.deleteNote(note)
        }
    }

}