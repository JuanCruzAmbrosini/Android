package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CardGrid(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TopicCard(topic: Topic,
               modifier: Modifier = Modifier) {

    Card (modifier = modifier)
    {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier.size(68.dp)
            )

            Column (

                modifier = Modifier.padding(start = 16.dp)

            ) {

                Text(
                    text = stringResource(topic.stringResourceId),
                    modifier = Modifier
                        .padding(top = 16.dp, end = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(

                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = stringResource(R.string.grain),
                    )

                    Text(
                        text = topic.numberOfCourses.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )

                }

            }
        }
    }
}

@Composable
fun CardGrid(
    modifier: Modifier = Modifier
)
{
    val topicList: DataSource = DataSource

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {

        items(topicList.topics){ topic ->

            TopicCard(topic)

        }

    }

}

@Preview(showBackground = true, showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait")
@Composable
fun GreetingPreview() {
    CoursesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CardGrid(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}