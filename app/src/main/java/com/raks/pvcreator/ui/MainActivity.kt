package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PVCreatorTheme {
                var colorIndex by remember { mutableStateOf(0) }
                val colors = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)

                LaunchedEffect(Unit) {
                    while (true) {
                        delay(1000)
                        colorIndex = (colorIndex + 1) % colors.size
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    colors[colorIndex],
                                    colors[(colorIndex + 1) % colors.size]
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.Center),
                        color = colors[colorIndex],
                        content = {}
                    )
                }


//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PVCreatorTheme {
        var colorIndex by remember { mutableStateOf(0) }
        val colors = listOf(Color.Blue, Color.Red, Color.Green, Color.Yellow)

        LaunchedEffect(Unit) {
            while (true) {
                delay(1000)
                colorIndex = (colorIndex + 1) % colors.size
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colors[colorIndex],
                            colors[(colorIndex + 1) % colors.size]
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
                color = colors[colorIndex],
                content = {}
            )
        }
    }
}