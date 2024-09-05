package com.mbandel.database

import android.content.Context
import androidx.room.Room
import com.mbandel.database.DatabaseConst.DATABASE_NAME
import com.mbandel.database.dao.FavoriteMovieIdsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieListDatabase {
        return Room.databaseBuilder(
            context,
            MovieListDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesFavoriteMovieIdsDao(database: MovieListDatabase): FavoriteMovieIdsDao {
        return database.favoriteMovieIdsDao()
    }
}