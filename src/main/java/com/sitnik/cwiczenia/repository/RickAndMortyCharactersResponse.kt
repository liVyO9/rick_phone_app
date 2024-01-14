package com.sitnik.cwiczenia.repository

data class RickAndMortyCharactersResponse(
    val info: Info,
    val results: List<Charakter>
)

data class Info(
    val count: Int,
    val pages: Int
)

data class Charakter(
    val name: String,
    val status: String,
    val species: String,
    val image: String
)