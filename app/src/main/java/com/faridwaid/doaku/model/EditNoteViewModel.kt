package com.faridwaid.doaku.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.faridwaid.doaku.database.Note
import com.faridwaid.doaku.database.NoteDao
import com.faridwaid.doaku.database.NoteRoomDatabase

class EditNoteViewModel(application: Application): AndroidViewModel(application) {

    private var noteDao: NoteDao?
    private var noteDB: NoteRoomDatabase?

    init {
        noteDB = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDB?.noteDao()
    }

    fun getNote(noteId: String): LiveData<Note>? = noteDao?.getNote(noteId)

}