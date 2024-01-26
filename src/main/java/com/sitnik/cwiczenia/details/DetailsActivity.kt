package com.sitnik.cwiczenia.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sitnik.cwiczenia.MyErrorView
import com.sitnik.cwiczenia.MyLoadingView
import com.sitnik.cwiczenia.R
import com.sitnik.cwiczenia.RickAndMortyDisplay
import com.sitnik.cwiczenia.UiState
import com.sitnik.cwiczenia.ui.theme.CwiczeniaTheme

class DetailsActivity : ComponentActivity() {
    private val viewModel: DetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("CUSTOM_KEY", 1)

        viewModel.getData(id)

        setContent {
            CwiczeniaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharacterDetailsDisplay(viewModel = viewModel)
                }
            }
        }
        Toast.makeText(this, "details id: $id", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun CharacterDetailsDisplay(viewModel: DetailsViewModel){
    val uiState by viewModel.mutableLiveDataRickAndMortyCharacter.observeAsState(UiState())
    when{
        uiState.isLoading -> {
            MyLoadingView()
        }
        uiState.error != null -> {
            MyErrorView()
        }
        uiState.data != null -> {
            val (name, status, species, gender, origin, location, image) = uiState.data!!
            CharacterDisplay(name = name, status = status, species = species, gender = gender, origin = origin.name, location = location.name, image = image)
        }
    }
}

@Composable
fun CharacterDisplay(name: String, status: String, species: String, gender:String, origin:String, location: String, image: String){
    Column{
        AsyncImage(model = image, contentDescription = "opis", modifier = Modifier.fillMaxWidth())
        Text(text = name, fontSize = 60.sp, modifier = Modifier.padding(all = 2.dp))
        Text(text = "$status - $species", color = Color.Gray,fontSize = 20.sp, modifier = Modifier.padding(bottom = 20.dp, end = 2.dp, start = 2.dp, top = 2.dp  ))
        Text(text = "Gender: $gender", fontSize = 30.sp, modifier = Modifier.padding(all = 2.dp))
        Text(text = "Origin: $origin", fontSize = 30.sp, modifier = Modifier.padding(all = 2.dp))
        Text(text = "Location: $location", fontSize = 30.sp, modifier = Modifier.padding(all = 2.dp))

    }
}