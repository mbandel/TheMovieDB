package com.mbandel.domain.usecase


import com.mbandel.database.dao.FavoriteMovieIdsDao
import com.mbandel.database.entity.FavoriteMovieIdsEntity
import javax.inject.Inject

class AddFavoriteMovieIdUseCase @Inject constructor(
    private val favoriteMovieIdsDao: FavoriteMovieIdsDao
): suspend (Int) -> Unit {
    override suspend fun invoke(id: Int) {
        favoriteMovieIdsDao.addMovieId(FavoriteMovieIdsEntity(id))
    }
}