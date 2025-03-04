package com.example.a30daysofgames

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.a30daysofgames.model.Game
import com.example.a30daysofgames.model.GamesDataSource
import com.example.a30daysofgames.ui.theme.GameCard
import com.example.a30daysofgames.ui.theme.GameTopBar
import com.example.a30daysofgames.ui.theme._30DaysOfGamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysOfGamesTheme {

                GameApp()

            }
        }
    }
}

@Composable
fun GameApp(){

    val games: List<Game> = GamesDataSource.GamesRepository

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GameTopBar()
        }
    )
    {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            contentPadding = it,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            itemsIndexed(games){index, game ->

                GameCard(
                    game = game,
                    index = index + 1,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small)),
                    cardWidth = 330
                )

            }

        }
    }

}

@Preview("Light Theme", showSystemUi = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GamePreview() {

    _30DaysOfGamesTheme {
        GameApp()
    }
}