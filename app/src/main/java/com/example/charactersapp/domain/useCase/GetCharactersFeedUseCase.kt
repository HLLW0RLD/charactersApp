package com.example.charactersapp.domain.useCase

import com.example.charactersapp.domain.repository.ILocalRepository
import com.example.charactersapp.domain.repository.IRemoteRepository

class GetCharactersFeedUseCase(
    val remoteRepository: IRemoteRepository,
    val localRepository: ILocalRepository
) {

}