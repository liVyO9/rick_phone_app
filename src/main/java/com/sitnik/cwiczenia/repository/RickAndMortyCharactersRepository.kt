package com.sitnik.cwiczenia.repository

import retrofit2.Response

class RickAndMortyCharactersRepository {

    suspend fun getAllRickAndMortyCharactersResponse(): Response<RickAndMortyCharactersResponse> =
        RickAndMortyCharactersService.rickAndMortyCharactersService.getAllRickAndMortyCharactersResponse()

    suspend fun getRickAndMortyCharacterResponse(characterId: Int): Response<RickAndMortyCharacterResponse> =
        RickAndMortyCharactersService.rickAndMortyCharactersService.getRickAndMortyCharacterResponse(characterId = characterId)
}