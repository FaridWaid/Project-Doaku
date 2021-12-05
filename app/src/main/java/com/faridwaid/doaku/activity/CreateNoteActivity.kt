package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import com.faridwaid.doaku.R

class CreateNoteActivity : AppCompatActivity() {

    companion object{
        val NEW_NOTE = "new_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        val et_note : TextView = findViewById(R.id.et_note)
        val btn_save_note : Button = findViewById(R.id.btn_save_note)
        btn_save_note.setOnClickListener {
            val resultIntent = Intent()
            if (!TextUtils.isEmpty(et_note.text)){
                val note = et_note.text.toString()
                resultIntent.putExtra(NEW_NOTE, note)

                setResult(RESULT_OK, resultIntent)
            } else {
                setResult(RESULT_CANCELED)
            }
            finish()
        }

    }
}