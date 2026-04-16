package com.example.charactersapp.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.domain.repository.IRemoteRepository
import com.example.charactersapp.presentation.utils.logError
import java.io.IOException

class CharacterPagingSource(
    private val repo: IRemoteRepository
) : PagingSource<Int, CharacterFeedModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterFeedModel> {
        return try {
            val page = params.key ?: 1
            val pageData = repo.getAllCharacters(page)

            if (pageData.isSuccess) {
                LoadResult.Page(
                    data = pageData.characters,
                    prevKey = if (pageData.hasPreviousPage) page - 1 else null,
                    nextKey = if (pageData.hasNextPage) page + 1 else null
                )
            } else {
                logError("Failed to load characters for page $page")

                LoadResult.Error(IOException("Failed to load characters for page $page"))
            }

        } catch (e: Exception) {
            logError(e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterFeedModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.let { prevKey ->
                prevKey + 1
            } ?: state.closestPageToPosition(anchorPosition)?.nextKey?.let { nextKey ->
                nextKey - 1
            }
        } ?: 1
    }
}