package com.faridwaid.doaku.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite (

    @PrimaryKey
    @NonNull
    var id: String,

    @NonNull
    @ColumnInfo(name = "doa")
    var doa: String,

    @NonNull
    @ColumnInfo(name = "ayat")
    var ayat: String,

    @NonNull
    @ColumnInfo(name = "latin")
    var latin: String,

    @NonNull
    @ColumnInfo(name = "artinya")
    var artinya: String

)