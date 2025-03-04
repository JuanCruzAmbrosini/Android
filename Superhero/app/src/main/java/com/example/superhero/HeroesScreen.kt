package com.example.superhero

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superhero.model.Hero
import com.example.superhero.model.HeroesRepository
import com.example.superhero.ui.theme.Shapes
import com.example.superhero.ui.theme.SuperheroTheme

class HeroesScreen{

    @Composable
    fun SuperheroApp(){

        Scaffold {

            LazyColumn (contentPadding = it){

                items(HeroesRepository.heroes){

                    SuperheroCard(
                        it,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))

                }
            }
        }
    }

    @Composable
    fun SuperheroCard(
        superhero: Hero = Hero(
            name = R.string.hero1,
            description = R.string.description1,
            image = R.drawable.android_superhero1
        ),
        modifier: Modifier = Modifier
    ) {

        Card (modifier = modifier,
            shape = Shapes.medium) {

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                SuperheroInformation(
                    heroName = superhero.name,
                    heroDescription = superhero.description,
                    modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp))

                SuperheroIcon(
                    heroImage = superhero.image,
                    modifier = Modifier
                    .size(72.dp))

            }

        }

    }

    @Composable
    fun SuperheroIcon(
        @DrawableRes heroImage: Int = R.drawable.android_superhero1,
        modifier: Modifier = Modifier
    ){

        Box (modifier = modifier) {
            Image(
                painter = painterResource(heroImage),
                contentDescription = null,
                modifier = Modifier.clip(Shapes.small)
            )
        }

    }

    @Composable
    fun SuperheroInformation(
        @StringRes heroName: Int = R.string.hero1 ,
        @StringRes heroDescription: Int = R.string.description1,
        modifier: Modifier = Modifier
    )
    {

        Column (
            modifier = modifier,
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = stringResource(heroName),
                style = MaterialTheme.typography.displaySmall
            )

            Text(
                text = stringResource(heroDescription),
                style = MaterialTheme.typography.bodyLarge.copy(lineHeight = 20.sp),
                overflow = TextOverflow.Ellipsis,
            )

        }

    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun CardLightPreview() {
        SuperheroTheme {
            SuperheroApp()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CardDarkPreview() {
        SuperheroTheme (darkTheme = true) {
            SuperheroApp()
        }
    }

}