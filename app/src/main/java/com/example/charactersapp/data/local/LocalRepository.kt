package com.example.charactersapp.data.local

import com.example.charactersapp.data.local.db.CharactersDao
import com.example.charactersapp.domain.repository.ILocalRepository

class LocalRepository(val dao: CharactersDao): ILocalRepository {


}