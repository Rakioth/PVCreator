package com.raks.pvcreator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raks.pvcreator.R
import com.raks.pvcreator.picker.PvPicker
import com.raks.pvcreator.ui.theme.PVCreatorTheme
import com.raks.pvcreator.util.*

class MainActivity : ComponentActivity() {

    companion object {
        const val BARCODE_ROWS = 4
        const val BARCODE_COLUMNS = 113
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Greeting()
            Pickers()
//            PVCreatorTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

@Composable
fun Pickers() {
    PVCreatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                PvPicker(
                    pickerOptions = emptyList()
                ) { snappedOption ->
                    println(snappedOption)
                }
                PvPicker(
                    pickerOptions = emptyList()
                ) { snappedOption ->
                    println(snappedOption)
                }
                PvPicker(
                    pickerOptions = emptyList()
                ) { snappedOption ->
                    println(snappedOption)
                }
                PvPicker(
                    pickerOptions = emptyList()
                ) { snappedOption ->
                    println(snappedOption)
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var text by remember { mutableStateOf(TextFieldValue("Text")) }

    PVCreatorTheme {
        PlasmaBackground(
            colors = arrayOf(
                Color(0xFF00BDFE),
                Color(0xFF6FE5C9),
                Color(0xFFF0C13F),
                Color(0xffD1196C),
                Color(0xff873F87)
            ),
        )
        PvCard(
            painter = painterResource(R.drawable.ic_pv_card)
        ) {
            Row {
                Spacer(
                    modifier = Modifier
                        .weight(0.127f)
                )
                Column(
                    modifier = Modifier
                        .weight(0.683f)
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(0.34f)
                    )
                    Column(
                        modifier = Modifier
                            .weight(0.12f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .weight(0.46f)
                                .padding(
                                    top = 2f.ph,
                                    bottom = 0.6f.ph
                                )
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.Bottom),
                            fontWeight = FontWeight.W600,
                            fontSize = 2.5f.sh,
                            lineHeight = 3.3f.sh,
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.54f)
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                                .background(
                                    color = Color(0xFFF9F9F9),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                        ) {
                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 3.1.pw,
                                        vertical = 1.6.ph
                                    )
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.Bottom)
                                ,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 2.5f.sh,
                                    lineHeight = 3.3f.sh,
                                )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.12f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .weight(0.46f)
                                .padding(
                                    top = 2f.ph,
                                    bottom = 0.6f.ph
                                )
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.Bottom),
                            fontWeight = FontWeight.W600,
                            fontSize = 2.5f.sh,
                            lineHeight = 3.3f.sh,
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.54f)
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                                .background(
                                    color = Color(0xFFF9F9F9),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                        ) {
                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 3.1.pw,
                                        vertical = 1.6.ph
                                    )
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.Bottom)
                                ,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 2.5f.sh,
                                    lineHeight = 3.3f.sh,
                                )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.12f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .weight(0.46f)
                                .padding(
                                    top = 2f.ph,
                                    bottom = 0.6f.ph
                                )
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.Bottom),
                            fontWeight = FontWeight.W600,
                            fontSize = 2.5f.sh,
                            lineHeight = 3.3f.sh,
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.54f)
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                                .background(
                                    color = Color(0xFFF9F9F9),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                        ) {
                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 3.1.pw,
                                        vertical = 1.6.ph
                                    )
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.Bottom)
                                ,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 2.5f.sh,
                                    lineHeight = 3.3f.sh,
                                )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.12f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .weight(0.46f)
                                .padding(
                                    top = 2f.ph,
                                    bottom = 0.6f.ph
                                )
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.Bottom),
                            fontWeight = FontWeight.W600,
                            fontSize = 2.5f.sh,
                            lineHeight = 3.3f.sh,
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.54f)
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                                .background(
                                    color = Color(0xFFF9F9F9),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                        ) {
                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 3.1.pw,
                                        vertical = 1.6.ph
                                    )
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.Bottom)
                                ,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 2.5f.sh,
                                    lineHeight = 3.3f.sh,
                                )
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.12f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Title",
                            modifier = Modifier
                                .weight(0.46f)
                                .padding(
                                    top = 2f.ph,
                                    bottom = 0.6f.ph
                                )
                                .fillMaxSize()
                                .wrapContentHeight(align = Alignment.Bottom),
                            fontWeight = FontWeight.W600,
                            fontSize = 2.5f.sh,
                            lineHeight = 3.3f.sh,
                        )
                        Box(
                            modifier = Modifier
                                .weight(0.54f)
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(1.dp, Color(0xFFE5E5EA)),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                                .background(
                                    color = Color(0xFFF9F9F9),
                                    shape = RoundedCornerShape(2f.pw)
                                )
                        ) {
                            BasicTextField(
                                value = text,
                                onValueChange = { text = it },
                                modifier = Modifier
                                    .padding(
                                        horizontal = 3.1.pw,
                                        vertical = 1.6.ph
                                    )
                                    .fillMaxSize()
                                    .wrapContentHeight(align = Alignment.Bottom)
                                ,
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontWeight = FontWeight.W400,
                                    fontSize = 2.5f.sh,
                                    lineHeight = 3.3f.sh,
                                )
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(0.06f)
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(0.19f)
                ) {
                    Spacer(
                        modifier = Modifier
                            .weight(0.08f)
                    )
                    Row(
                        modifier = Modifier
                            .weight(0.84f)
                    ) {
                        Spacer(
                            modifier = Modifier
                                .weight(0.33f)
                        )
                        PvBarcode(
                            modifier = Modifier
                                .weight(0.33f)
                                .fillMaxSize(),
                            barcodePattern = listOf(
                                "10010001010011101100010100001000100101000010100111010001100110110001101011100101100010100001000010100100010011011",
                                "10001011000100101000110010001000100101100110011101011000100011010011001010000101000110100111011100101110010011001"
                            )
                        )
                        Spacer(
                            modifier = Modifier
                                .weight(0.33f)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(0.08f)
                    )
                }
            }
        }

    }
}


@Composable
fun PvBarcode(
    modifier: Modifier,
    barcodePattern: List<String>
) {
    Canvas(
        modifier = modifier,
        onDraw = {
            val barcodeWidth = size.width / MainActivity.BARCODE_ROWS
            val barcodeHeight = size.height / MainActivity.BARCODE_COLUMNS

            barcodePattern.forEachIndexed { index, row ->
                val reverseIndex = barcodePattern.size - index
                val x = size.width - barcodeWidth * reverseIndex

                row.forEachIndexed { offset, char ->
                    val color = if (char == '1') Color.Black else Color.Transparent
                    val y = offset * barcodeHeight
                    drawRect(color, Offset(x, y), Size(barcodeWidth, barcodeHeight))
                }
            }
        }
    )
}

@Composable
fun PvCard(
    painter: Painter,
    content: @Composable () -> Unit
) {
    val ratio = painter.intrinsicSize.width / painter.intrinsicSize.height

    var size by remember { mutableStateOf(IntSize.Zero) }

    initSize(LocalContext.current, size)

    Box(
        modifier = Modifier
            .background(Transparent)
            .padding(15.dp)
            .wrapContentSize(Alignment.TopCenter)
            .aspectRatio(ratio)
            .let {
                if (ratio > 1)
                    it.height(IntrinsicSize.Max)
                else
                    it.width(IntrinsicSize.Max)
            }
            .onSizeChanged { size = it },
    ) {
        Image(
            painter = painter,
            contentDescription = "Piñata Vision Card",
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}

@Preview(name = "TEST", widthDp = 700, heightDp = 1524)
@Composable
fun DefaultPreview() {
    Greeting()
}