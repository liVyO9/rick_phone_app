package com.sitnik.cwiczenia.repository

import retrofit2.Response

class RickAndMortyCharactersRepository {

    suspend fun getRickAndMortyCharactersResponse(): Response<RickAndMortyCharactersResponse> =
        RickAndMortyCharactersService.rickAndMortyCharactersService.getRickAndMortyCharactersResponse()
}