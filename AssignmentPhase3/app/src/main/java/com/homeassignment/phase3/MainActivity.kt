package com.homeassignment.phase3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homeassignment.phase3.ui.theme.ScribdPhase3Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScribdPhase3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.color_on_surface)
                ) {
                    var toAllCaps by remember { mutableStateOf(false) }
                    var enteredValue by rememberSaveable { mutableStateOf("") }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Data(toAllCaps, enteredValue)

                        Control(toAllCaps,
                            enteredValue,
                            onToAllCapsChange = { toAllCaps = !toAllCaps },
                            onEnteredValueChange = { enteredValue = it }
                        )

                    }

                }
            }
        }
    }
}


@Composable
fun Data(
    toAllCaps: Boolean,
    enteredValue: String,
) {

    Column(
    ) {
        val context = LocalContext.current
        Button(
            onClick = { context.startActivity(Intent(context, ListScreen::class.java)) },
            modifier = Modifier
                .padding(start = 250.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.teal_700),
                contentColor = colorResource(R.color.teal_200)
            )
        ) {
            Text(text = "Next Screen", fontSize = 18.sp, fontFamily = FontFamily.SansSerif)
            Spacer(modifier = Modifier.imePadding())
        }
        Spacer(modifier = Modifier.height(30.dp))
        if (!toAllCaps) {
            Text(
                text = enteredValue.lowercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 18.dp)
                    .background(color = colorResource(R.color.white))
                    .height(80.dp),
                textAlign = TextAlign.Center,
                color = colorResource(R.color.teal_700),
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,

                )
        } else {
            Text(
                text = enteredValue.uppercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .background(color = colorResource(R.color.white))
                    .height(80.dp)
                    .border(BorderStroke(1.dp, colorResource(R.color.teal_200))),
                textAlign = TextAlign.Center,
                color = colorResource(R.color.teal_700),
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
            )
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Control(
    toAllCaps: Boolean,
    enteredValue: String,
    onToAllCapsChange: () -> Unit,
    onEnteredValueChange: (String) -> Unit,
) {
    val coroutinescope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester();

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = enteredValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutinescope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.teal_200),
                unfocusedIndicatorColor = colorResource(R.color.teal_500),
                cursorColor = colorResource(R.color.teal_500)
            ),
            textStyle = TextStyle(
                color = colorResource(R.color.teal_700),
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            ),
            label = { Text(text = "Enter Text", color = colorResource(R.color.teal_700)) },
            onValueChange = onEnteredValueChange,
        )
        Button(
            onClick = onToAllCapsChange,
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .padding(all = 20.dp)
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.teal_700),
                contentColor = colorResource(R.color.teal_200)
            )
        ) {
            Text(text = "Change Case", fontSize = 18.sp, fontFamily = FontFamily.SansSerif)
            Spacer(modifier = Modifier.imePadding())

        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScribdPhase3Theme {
        //  Data("Android")
    }
}