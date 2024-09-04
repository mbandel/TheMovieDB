package com.mbandel.domain.usecase


import com.mbandel.database.dao.FavoriteMovieIdsDao
import com.mbandel.database.entity.FavoriteMovieIdsEntity
import javax.inject.Inject

class RemoveFavoriteMovieIdUseCase @Inject constructor(
    private val favoriteMovieIdsDao: FavoriteMovieIdsDao
): suspend (Int) -> Unit {
    override suspend fun invoke(id: Int) {
        favoriteMovieIdsDao.removeMovieId(FavoriteMovieIdsEntity(id))
    }
}