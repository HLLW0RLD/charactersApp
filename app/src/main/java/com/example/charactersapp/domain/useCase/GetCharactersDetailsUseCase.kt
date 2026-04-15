package com.example.charactersapp.domain.useCase

import com.example.charactersapp.domain.repository.ILocalRepository
import com.example.charactersapp.domain.repository.IRemoteRepository

class GetCharactersDetailsUseCase(
    val remoteRepository: IRemoteRepository,
    val localRepository: ILocalRepository
) {

}