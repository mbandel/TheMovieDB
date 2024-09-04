package com.mbandel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbandel.database.dao.FavoriteMovieIdsDao
import com.mbandel.database.entity.FavoriteMovieIdsEntity

@Database(
    entities = [FavoriteMovieIdsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieListDatabase : RoomDatabase() {
    abstract fun favoriteMovieIdsDao(): FavoriteMovieIdsDao
}