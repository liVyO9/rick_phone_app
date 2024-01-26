package com.sitnik.cwiczenia.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sitnik.cwiczenia.UiState
import com.sitnik.cwiczenia.repository.RickAndMortyCharacterResponse
import com.sitnik.cwiczenia.repository.RickAndMortyCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val rickAndMortyCharactersRepository = RickAndMortyCharactersRepository()
    val mutableLiveDataRickAndMortyCharacter = MutableLiveData<UiState<RickAndMortyCharacterResponse>>()
    fun getData(characterId:Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val request = rickAndMortyCharactersRepository.getRickAndMortyCharacterResponse(characterId)
                val rickAndMortyCharacter = request.body()
                mutableLiveDataRickAndMortyCharacter.postValue(UiState(rickAndMortyCharacter))
            }catch (e: Exception){
                Log.e("DetailsViewModel", "request failed, exception", e)
            }
        }
    }
}