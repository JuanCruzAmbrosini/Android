package com.example.lemonade

import android.media.Image
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                    LemonadeAppPreview()
                }
            }
        }
    }


@Composable
fun LemonadeApp(
    modifier: Modifier = Modifier){

    var view by remember { mutableIntStateOf(1) }
    var squeezeCount = 0
    var squeeze = if (view == 2) { (2..4).random() } else {0}

    var mainImage = painterResource(R.drawable.lemon_tree)
    var mainText = stringResource(R.string.lemon_tree_text)
    var descriptionText = stringResource(R.string.lemon_tree_content_description)

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    when (view){

        1 ->{
            mainImage = painterResource(R.drawable.lemon_tree)
            mainText = stringResource(R.string.lemon_tree_text)
            descriptionText = stringResource(R.string.lemon_tree_content_description)
        }
        2 ->{
            mainImage = painterResource(R.drawable.lemon_squeeze)
            mainText = stringResource(R.string.lemon_text)
            descriptionText = stringResource(R.string.lemon_content_description)
        }
        3 -> {
            mainImage = painterResource(R.drawable.lemon_drink)
            mainText = stringResource(R.string.lemonade_glass_text)
            descriptionText = stringResource(R.string.lemonade_glass_content_description)
        }
        4 -> {
            mainImage = painterResource(R.drawable.lemon_restart)
            mainText = stringResource(R.string.empty_glass_text)
            descriptionText = stringResource(R.string.empty_glass_content_description)
        }


    }

    Spacer(Modifier.windowInsetsPadding(WindowInsets.statusBars))

    Text(
        text = stringResource(R.string.lemonade),
        Modifier
            .fillMaxWidth()
            .background(color = Color(0xFFf8e46c))
            .padding(WindowInsets.statusBars.asPaddingValues())
            .padding(32.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,

    )

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {

        Button(
            onClick = {

                when (view){
                    1 -> view++
                    2 -> {
                        squeezeCount++
                        if (squeezeCount == squeeze){
                            view++
                        }
                    }
                    3 -> view++
                    4 -> {
                        view = 1
                        squeezeCount = 0
                    }
                }

                      },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFc3ecd2)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.size(250.dp)

        ) {

            Image(

                painter = mainImage,
                contentDescription = descriptionText,

            )

        }

        Spacer(
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = mainText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            fontSize = 18.sp
        )

    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp(Modifier
            .fillMaxSize()
            .wrapContentSize())
    }
}