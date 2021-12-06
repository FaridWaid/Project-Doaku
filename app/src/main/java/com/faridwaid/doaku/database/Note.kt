package com.faridwaid.doaku.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey
    @NonNull
    var id: String,

    @NonNull
    @ColumnInfo(name = "title")
    var title: String,

    @NonNull
    @ColumnInfo(name = "note")
    var note: String
)