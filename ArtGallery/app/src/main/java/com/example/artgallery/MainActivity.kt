package com.example.artgallery

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtGalleryApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun ArtGalleryApp(modifier: Modifier = Modifier) {


    var actualView by remember { mutableIntStateOf(1) }

    val fields = R.drawable::class.java.fields
    val numberOfPaintings = fields.size

    val context = LocalContext.current

    when (actualView) {

        1 -> {
            ArtGalleryLayout(
                onClickFunctionPrevious = { actualView = (numberOfPaintings - 2) },
                onClickFunctionNext = { actualView++ },
                title = stringResource(R.string.title_1),
                descriptionText = stringResource(R.string.description_text_1),
                year = stringResource(R.string.year_1),
                author = stringResource(R.string.author_1),
                image = painterResource(R.drawable.picture_1),
                modifier = Modifier
            )
        }

        in (2..(numberOfPaintings - 3)) -> {

            ArtGalleryLayout(
                onClickFunctionPrevious = { actualView-- },
                onClickFunctionNext = { actualView++ },
                title = getDynamicStringResource("title_", actualView, context),
                descriptionText = getDynamicStringResource("description_text_",actualView, context),
                year = getDynamicStringResource("year_", actualView, context),
                author = getDynamicStringResource("author_", actualView, context),
                image = getDynamicImageResource(actualView, context),
                modifier = Modifier
            )
        }

        else -> {

            ArtGalleryLayout(
                onClickFunctionPrevious = { actualView-- },
                onClickFunctionNext = { actualView = 1 },
                title = getDynamicStringResource("title_", actualView, context),
                descriptionText = getDynamicStringResource("description_text_",actualView, context),
                year = getDynamicStringResource("year_", actualView, context),
                author = getDynamicStringResource("author_", actualView, context),
                image = getDynamicImageResource(actualView, context),
                modifier = Modifier
            )

        }

    }

}


@Composable
fun ArtGalleryLayout(onClickFunctionPrevious: () -> Unit,
                     onClickFunctionNext: () -> Unit,
                     title: String,
                     descriptionText: String,
                     author: String,
                     year: String,
                     image: Painter?,
                     modifier: Modifier = Modifier) {

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(Modifier.weight(0.5f))

        Surface (

            shadowElevation = 10.dp,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .border(width = 2.dp, color = Color(0xFF545496))
                .weight(0.7f)
            ) {
            if (image != null) {
                Image(
                    painter = image,
                    contentDescription = descriptionText,
                    modifier = Modifier.padding(32.dp)
                )
            }
        }

        Spacer(Modifier.weight(0.5f))

        TextColumn(
            title = title,
            author = author,
            year = year,
            onClickFunctionPrevious = onClickFunctionPrevious,
            onClickFunctionNext = onClickFunctionNext,
            descriptionText = descriptionText,
            image = image,
            modifier = Modifier
                .weight(0.45f)
                .width(500.dp)
        )

    }
}


@Composable
fun TextColumn (
    title: String = stringResource(R.string.generic_title),
    author: String = stringResource(R.string.generic_author),
    year: String = stringResource(R.string.generic_year),
    onClickFunctionPrevious: () -> Unit,
    onClickFunctionNext: () -> Unit,
    descriptionText: String,
    image: Painter?,
    modifier: Modifier)

{

    Column (
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .background(color = Color(0xFFD1D1E5))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ){
        Column (
            modifier = Modifier.fillMaxWidth().wrapContentSize()
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 32.dp)
                    .fillMaxWidth(),
                fontSize = 26.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Start
            )
            Text(
                text = buildAnnotatedString {

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(title)
                    }
                    append(" $year")
                },
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, top = 16.dp, bottom = 32.dp),
                textAlign = TextAlign.Start
            )
        }
    }

    ButtonRow(onClickFunctionPrevious, onClickFunctionNext)

}

@Composable
fun ButtonRow(onClickFunctionPrevious: () -> Unit ,
              onClickFunctionNext: () -> Unit ,
              modifier: Modifier = Modifier){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 48.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {

        NextAndPreviousButton(buttonText = stringResource(R.string.previous_button),
            modifier = Modifier.weight(2f),
            onClickFunction = onClickFunctionPrevious
        )

        Spacer(modifier = Modifier.weight(0.5f))

        NextAndPreviousButton(buttonText = stringResource(R.string.next_button),
            modifier = Modifier.weight(2f),
            onClickFunction = onClickFunctionNext
        )
    }

}

@Composable
fun NextAndPreviousButton(buttonText: String = stringResource(R.string.sample_text)
                          , onClickFunction: () -> Unit
                          , modifier: Modifier = Modifier){
    Button(

        onClick = {onClickFunction()},
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF545496))

    ) {
        Text(

            text = buttonText

        )
    }
}

@Composable
fun getDynamicStringResource(prefix: String, idNumber: Int, context: Context): String {
    val resId = context.resources.getIdentifier(prefix + idNumber, "string", context.packageName)
    return if (resId != 0) context.getString(resId) else "String not found"
}

@Composable
fun getDynamicImageResource(imageNumber: Int, context: Context): Painter? {
    val resId = context.resources.getIdentifier("picture_$imageNumber", "drawable", context.packageName)
    return if (resId != 0) painterResource(resId) else null
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtGalleryTheme {
        ArtGalleryApp()
    }
}

