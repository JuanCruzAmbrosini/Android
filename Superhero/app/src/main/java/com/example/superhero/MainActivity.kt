package com.example.superhero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.superhero.model.HeroesRepository
import com.example.superhero.ui.theme.SuperheroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroTheme {
                SuperheroApp()
            }
        }
    }
}

@Composable
fun SuperheroApp(){

    val heroesScreen = HeroesScreen()

    Scaffold (
        topBar = {SuperheroTopBar()},
    ) {

        LazyColumn (contentPadding = it){

            items(HeroesRepository.heroes){

                heroesScreen.SuperheroCard(
                    it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperheroTopBar(){

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
                )
        }
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardLightPreview() {
    SuperheroTheme {
        SuperheroApp()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardDarkPreview() {
    SuperheroTheme (darkTheme = true) {
        SuperheroApp()
    }
}