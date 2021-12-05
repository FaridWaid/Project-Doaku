package com.faridwaid.doaku.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    fun getListFavorites(): LiveData<List<Favorite>>

}