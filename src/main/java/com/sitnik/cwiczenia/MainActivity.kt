package com.sitnik.cwiczenia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sitnik.cwiczenia.details.DetailsActivity
import com.sitnik.cwiczenia.repository.Charakter
import com.sitnik.cwiczenia.ui.theme.CwiczeniaTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getData()

        setContent {
            CwiczeniaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickAndMortyDisplay(viewModel = viewModel,
                        onClick = { id -> navigateToDetailsActivity(id)})
                }
            }
        }
    }

    fun navigateToDetailsActivity(id:String){
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("CUSTOM_KEY", id)
        startActivity(intent)
    }
}

@Composable
fun RickAndMortyDisplay(viewModel: MainViewModel){
    val uiState by viewModel.mutableLiveDataRickAndMorty.observeAsState(UiState())
    Log.d("AAA", "AAAA")
    when {
        uiState.isLoading -> { MyLoadingView() }

        uiState.error != null -> { MyErrorView() }

        uiState.data != null -> { uiState.data?.let { CharactersListDisplay(charakters = it) } }
    }
}

@Composable
fun MyErrorView() {
    Text(text = "Error", fontSize = 20.sp, modifier = Modifier.padding(all = 2.dp))
}

@Composable
fun MyLoadingView() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
fun CharactersListDisplay(charakters: List<Charakter>, onClick: (String) -> Unit) {
    if (charakters.isNotEmpty()){
//        rickAndMorty.forEach{ element ->
//            Log.d("MainActivity", element.name)
//        }
        LazyColumn{
                items(charakters){
                   element ->
                    CharacterDisplay(name = element.name, status = element.status, species = element.species, url = element.image, onClick = { id -> onClick.invoke(id)})
                }
        }
    }
}


@Composable
fun CharacterDisplay(name:String, status: String, species:String, url:String, onClick: (String) -> Unit){
    Row {
        AsyncImage(model = url, contentDescription = "opis", modifier = Modifier.size(200.dp))
//        Image(painter = painterResource(id = R.drawable.rick), contentDescription = "Rick", modifier = Modifier.size(200.dp))
        Column (
//            zakładam, że id mojego elementu/kafelka to pole name
            modifier = Modifier.clickable { onClick.invoke(name) }
        ){
            Text(text = name, fontSize = 20.sp, modifier = Modifier.padding(all = 2.dp))
            Text(text = "$status - $species", fontSize = 10.sp, modifier = Modifier
                .padding(all = 2.dp)
                .padding(bottom = 10.dp))
//            Text(text = "Last known location:", fontSize = 10.sp, color = Color.Gray, modifier = Modifier.padding(all = 2.dp))
//            Text(text = "Rickmurai Jack", fontSize = 11.sp, modifier = Modifier
//                .padding(all = 2.dp)
//                .padding(bottom = 10.dp))
//            Text(text = "First seen in:", fontSize = 10.sp, color = Color.Gray, modifier = Modifier.padding(all = 2.dp))
//            Text(text = "Pilot", fontSize = 11.sp, modifier = Modifier
//                .padding(all = 2.dp)
//                .padding(bottom = 5.dp))
        }
    }
}

@Composable
fun Widok(){
    Column {
        Text(text = "Dlaczego", modifier = Modifier.padding(all = 12.dp))
        Row {
            Text(text="Hello world!", color = Color.Green, modifier = Modifier.padding(top=10.dp))
            Text(text = " Lol", fontSize = 40.sp, modifier= Modifier
                .padding(all = 12.dp)
                .border(
                    BorderStroke(1.dp, Color.Blue)
                ))
            Text(text = "Ala ma kota", fontStyle = FontStyle.Italic)
        }
        Image(painter = painterResource(id = R.drawable.obrazek), contentDescription = "alek", colorFilter = ColorFilter.tint(Color.Magenta, BlendMode.Color))
    }
}

@Preview(showBackground = true)
@Composable
fun RickPreview() {
    CwiczeniaTheme {
        Widok()
    }
}