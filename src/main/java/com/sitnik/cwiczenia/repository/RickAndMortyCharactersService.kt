package com.sitnik.cwiczenia.repository

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyCharactersService {

    @GET("/api/character")
    suspend fun getAllRickAndMortyCharactersResponse(): Response<RickAndMortyCharactersResponse>

    @GET("api/character/{id}")
    suspend fun getRickAndMortyCharacterResponse(@Path("id") characterId:Int): Response<RickAndMortyCharacterResponse>

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/"

        private val retrofit: Retrofit by lazy {
           Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
        }

        val rickAndMortyCharactersService: RickAndMortyCharactersService by lazy {
            retrofit.create(RickAndMortyCharactersService::class.java)
        }
    }
}