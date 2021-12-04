package com.faridwaid.doaku.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.faridwaid.doaku.database.Favorite
import com.faridwaid.doaku.database.FavoriteDao
import com.faridwaid.doaku.database.FavoriteRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var favoriteDao: FavoriteDao?
    private var favoriteDB: FavoriteRoomDatabase?
    private var list: LiveData<List<Favorite>>

    init {
        favoriteDB = FavoriteRoomDatabase.getDatabase(application)
        favoriteDao = favoriteDB?.favoriteDao()
        list = favoriteDao?.getListFavorite()!!
    }

    fun insert(doaFavorite: String, ayatFavorite: String, latinFavorite: String, artiFavorite: String){
        insertToDatabase(doaFavorite, ayatFavorite, latinFavorite, artiFavorite)
    }

    fun getListNotes(): LiveData<List<Favorite>> = list

    private fun insertToDatabase(doaFavorite: String, ayatFavorite: String, latinFavorite: String, artiFavorite: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val favoriteId = UUID.randomUUID().toString()
            val favorite = Favorite(
                favoriteId,
                doaFavorite,
                ayatFavorite,
                latinFavorite,
                artiFavorite
            )
            favoriteDao?.insert(favorite)
        }
    }



}