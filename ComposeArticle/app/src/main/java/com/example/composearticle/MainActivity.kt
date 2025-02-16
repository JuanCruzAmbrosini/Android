package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeTutorial()
                }
            }
        }
    }
}

@Composable
fun ComposeTutorial(
    title: String = stringResource(R.string.title),
    intro: String = stringResource(R.string.intro),
    content: String = stringResource(R.string.content),
    modifier: Modifier = Modifier) {

    Column {
        Image(

            painter = painterResource(id = R.drawable.bg_compose_background),
            contentDescription = null,
            modifier = modifier.fillMaxWidth()


        )

        Text(

            text = title,
            fontSize = 24.sp,
            modifier = modifier.padding(16.dp)

        )
        Text(

            text = intro,
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Justify

        )

        Text(

            text = content,
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Justify

        )

    }

}

@Preview(showBackground = true)
@Composable
fun ComposeTutorialPreview() {
    ComposeArticleTheme {
        ComposeTutorial()
    }
}