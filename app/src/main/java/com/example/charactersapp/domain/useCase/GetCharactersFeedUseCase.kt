package com.example.charactersapp.domain.useCase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.charactersapp.data.paging.CharacterPagingSource
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.repository.ILocalRepository
import com.example.charactersapp.domain.repository.IRemoteRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersFeedUseCase(
    val remoteRepository: IRemoteRepository,
    val localRepository: ILocalRepository

) {

    fun getCharactersFeed(): Flow<PagingData<CharacterFeedModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                CharacterPagingSource(
                    remoteRepository = remoteRepository,
                    localRepository = localRepository
                )
            }
        ).flow
    }
}