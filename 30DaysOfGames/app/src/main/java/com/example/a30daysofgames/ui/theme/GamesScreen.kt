package com.example.a30daysofgames.ui.theme

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofgames.R
import com.example.a30daysofgames.R.dimen
import com.example.a30daysofgames.model.Game

@Composable
fun GameCard(game: Game,
             index: Int = 1,
             modifier: Modifier = Modifier,
             cardWidth: Int = 330) {

    var expand by remember { mutableStateOf(false) }

//    Scaffold (
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            GameTopBar()
//        }
//    ) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            GameIntro(
                game.imageResourceId,
                game.titleResourceId,
                game.publishingResourceId,
                index
            )

            GameMoreButton(expand, { expand = !expand }, modifier = Modifier.width(cardWidth.dp))

            if (expand) {
                GameInfo(
                    gameDescription = game.descriptionResourceId,
                    modifier = Modifier
                        .width(cardWidth.dp)
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth().height(140.dp), // Ajusta la altura aquí también
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier.height(140.dp) // Ajusta la altura aquí
    )
}

@Composable
fun GameMoreButton(
    expand: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button),
            tint = MaterialTheme.colorScheme.tertiaryContainer
        )
    }

}

@Composable
fun GameIntro(
    @DrawableRes gameIcon: Int,
    @StringRes gameName: Int,
    @StringRes yearOfPublished: Int,
    index: Int,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .width(330.dp)
                .padding(dimensionResource(R.dimen.padding_small))
        ) {

            Text(

                text = "$index: ",
                style = MaterialTheme.typography.titleLarge

            )

            Text(

                text = stringResource(gameName),
                style = MaterialTheme.typography.titleMedium

            )

        }

        GameIcon(gameIcon, gameName)

        Text(

            text = stringResource(yearOfPublished),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.End,
            modifier = Modifier
                .width(330.dp)
                .padding(
                    top = dimensionResource(dimen.padding_small),
                    end = dimensionResource(dimen.padding_small),
                    bottom = dimensionResource(dimen.padding_small)
                )

        )
    }
}

@Composable
fun GameIcon(
    @DrawableRes gameIcon: Int,
    @StringRes gameName: Int,
    imageSize: Int = 330,
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = Modifier
            .border(
                2.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                shape = Shapes.medium
            )
    ) {
        Image(

            painter = painterResource(gameIcon),
            contentDescription = stringResource(gameName),
            modifier = Modifier
                .size(imageSize.dp)
                .padding(dimensionResource(dimen.padding_small))
                .clip(shape = Shapes.medium)

        )
    }

}

@Composable
fun GameInfo(
    @StringRes gameDescription: Int,
    modifier: Modifier = Modifier
) {

    Text(

        text = stringResource(gameDescription),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier

    )

}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GamePreview() {

    _30DaysOfGamesTheme {
        Scaffold {
            GameCard(
                Game(
                    R.string.portal_2,
                    R.string.description_portal_2,
                    R.string.publishing_portal_2,
                    R.drawable.portal_2,
                ),
                modifier = Modifier.padding(paddingValues = it)
            )
        }
    }
}