package com.sitnik.cwiczenia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sitnik.cwiczenia.repository.Charakter
import com.sitnik.cwiczenia.repository.RickAndMortyCharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    private val rickAndMortyCharactersRepository = RickAndMortyCharactersRepository()

    val mutableLiveDataRickAndMorty = MutableLiveData<UiState<List<Charakter>>>()



    fun getData(){

        viewModelScope.launch(Dispatchers.IO){
            try{
                val request = rickAndMortyCharactersRepository.getRickAndMortyCharactersResponse()
                Log.d("MainViewModel", "request response code: ${request.code()}")
                Log.d("MainViewModel1", "request response code: ${request.body()}")
                Log.d("MainViewModel2", "request response code: ${request.body()}")
                val rickAndMortyCharacters = request.body()?.results
                mutableLiveDataRickAndMorty.postValue(UiState(rickAndMortyCharacters))
            }catch (e: Exception){
                Log.e("MainViewModel", "request failed, exception", e)
            }
        }

    }
}