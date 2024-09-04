package com.mbandel.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbandel.database.DatabaseConst.FAVORITE_IDS_TABLE_NAME
import com.mbandel.database.entity.FavoriteMovieIdsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieIdsDao {

    @Query("SELECT * FROM $FAVORITE_IDS_TABLE_NAME")
    fun observeFavoriteMovieIds(): Flow<List<FavoriteMovieIdsEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovieId(favoriteMovieIdsEntity: FavoriteMovieIdsEntity)

    @Delete
    suspend fun removeMovieId(favoriteMovieIdsEntity: FavoriteMovieIdsEntity)

}