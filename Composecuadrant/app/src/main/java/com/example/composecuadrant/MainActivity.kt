package com.example.composecuadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecuadrant.ui.theme.ComposeCuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCuadrantTheme {
               Surface (
                   modifier = Modifier.fillMaxSize(),
                   color = MaterialTheme.colorScheme.background
               ) {

                   Cuadrants(
                       stringResource(R.string.title1),
                       stringResource(R.string.title2),
                       stringResource(R.string.title3),
                       stringResource(R.string.title4),
                       stringResource(R.string.description1),
                       stringResource(R.string.description2),
                       stringResource(R.string.description3),
                       stringResource(R.string.description4),
                       )

               }
            }
        }
    }
}

@Composable
fun Cuadrants(
    title1: String,
    title2: String,
    title3: String,
    title4: String,
    description1: String,
    description2: String,
    description3: String,
    description4: String,
    modifier: Modifier = Modifier
) {

    Column (modifier.fillMaxSize()) {
        Row (modifier.weight(1f)) {
            Column(
                modifier = modifier.weight(1f).fillMaxSize().background(color = Color(0xFFEADDFF)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.title1),
                    modifier = modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = stringResource(R.string.description1),
                    modifier = modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Justify
                )
            }

            Column (
                modifier = modifier.weight(1f).fillMaxSize().background(color = Color(0xFFD0BCFF)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.title2),
                    modifier = modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.description2),
                    modifier = modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
        Row(modifier.weight(1f)) {
            Column(
                modifier = modifier.weight(1f).fillMaxSize().background(color = Color(0xFFB69DF8)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.title3),
                    modifier = modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.description3),
                    modifier = modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Justify
                )
            }

            Column(
                modifier = modifier.weight(1f).fillMaxSize().background(color = Color(0xFFF6EDFF)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                Text(
                    text = stringResource(R.string.title4),
                    modifier = modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.description4),
                    modifier = modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeCuadrantTheme {
        Cuadrants(
            stringResource(R.string.title1),
            stringResource(R.string.title2),
            stringResource(R.string.title3),
            stringResource(R.string.title4),
            stringResource(R.string.description1),
            stringResource(R.string.description2),
            stringResource(R.string.description3),
            stringResource(R.string.description4)
            )
    }
}