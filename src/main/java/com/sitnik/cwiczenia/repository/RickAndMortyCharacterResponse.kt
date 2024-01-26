package com.sitnik.cwiczenia.repository

data class RickAndMortyCharacterResponse (
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: CharacterOrigin,
    val location: CharacterLocation,
    val image: String
)

data class CharacterOrigin (
    val name: String
)

data class CharacterLocation (
    val name: String
)