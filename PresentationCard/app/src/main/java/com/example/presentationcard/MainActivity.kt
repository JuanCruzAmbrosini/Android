package com.example.presentationcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Smartphone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentationcard.ui.theme.PresentationCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PresentationCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    FullCard()

                }
            }
        }
    }
}


@Composable
fun Intro(modifier: Modifier = Modifier){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(

            painter = painterResource(R.drawable.android_logo),
            contentDescription = stringResource(R.string.logo_descriptor),
            modifier
                .size(150.dp)
                .background(color = Color(0xFF073042))

        )

        Text(

            text = stringResource(R.string.name),
            modifier = modifier.padding(top = 4.dp, bottom = 4.dp, end = 16.dp, start = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            color = Color(0xFF073042),
            fontWeight = FontWeight.Bold,

        )

        Text(

            text = stringResource(R.string.title),
            modifier = modifier.padding(bottom = 16.dp, end = 16.dp, start = 16.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF006d3b)

            )

    }
}

@Composable
fun InfoSection (modifier: Modifier = Modifier){

    Column(

        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)

    ) {
        InformationBrick(
            info = stringResource(R.string.cellphone_number),
            contentDescription = stringResource(R.string.celphone_icon)
        )
        InformationBrick(
            icon = Icons.Filled.Share,
            info = stringResource(R.string.social_account),
            contentDescription = stringResource(R.string.share_icon)
        )
        InformationBrick(
            icon = Icons.Filled.Email,
            info = stringResource(R.string.email),
            contentDescription = stringResource(R.string.email_icon)

        )
    }

}

@Composable
fun InformationBrick (icon: ImageVector = Icons.Filled.Smartphone, info: String = stringResource(R.string.generic_info), contentDescription: String = stringResource(R.string.generic_icon), modifier: Modifier = Modifier){

    Row (

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,


    ){
        Icon(

            imageVector = icon,
            contentDescription = contentDescription,
            tint = Color(0xFF006d3b),
            modifier = modifier.padding(top = 5.dp, start = 5.dp, bottom = 5.dp)

        )

        Text(

            text = info,
            modifier = modifier.padding(8.dp),
            textAlign = TextAlign.Start,
            color = Color(0xFF19443c)

            )
    }

}

@Composable
fun FullCard(modifier: Modifier = Modifier){

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFd2e8d4)),
        verticalArrangement = Arrangement.SpaceAround

    ) {
        Spacer(modifier.height(1.dp))
        Intro()
        InfoSection()
    }

}

@Preview(showBackground = true)
@Composable
fun PresentationCardPreview() {
    PresentationCardTheme {
        FullCard()
    }
}