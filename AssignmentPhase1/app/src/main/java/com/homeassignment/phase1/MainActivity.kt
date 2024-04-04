package com.homeassignment.phase1

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.homeassignment.phase1.ui.theme.ScribdPhase1Theme
import androidx.compose.runtime.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScribdPhase1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(R.color.color_on_surface)
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val coroutinescope = rememberCoroutineScope()
    val bringIntoViewRequester = BringIntoViewRequester();

    var enteredValue by remember {
        mutableStateOf("")
    }
    var toAllCaps by remember {
        mutableStateOf(false)
    }


    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {

        if (!toAllCaps){
            Text(text =  enteredValue.lowercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .background(color = colorResource(R.color.white))
                    .height(80.dp),

                textAlign = TextAlign.Center,
                color= colorResource(R.color.teal_700),
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,

            )
        }else{
            Text(text =  enteredValue.uppercase(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp)
                    .background(color = colorResource(R.color.white))
                    .height(80.dp)
                    .border(BorderStroke(1.dp, colorResource(R.color.teal_200))),
                textAlign = TextAlign.Center,
                color= colorResource(R.color.teal_700),
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
            )
        }

        TextField(value = enteredValue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
                .onFocusEvent { event->
                    if(event.isFocused){
                        coroutinescope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
//            shape = RoundedCornerShape(8.dp),
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
            label = { Text(text = "Enter Text",color = colorResource(R.color.teal_700)) },
            onValueChange ={newText -> enteredValue=newText},
        )
        Button(
            onClick = {toAllCaps=!toAllCaps},
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
                .padding(all = 20.dp)
                .width(200.dp)
                .height(50.dp),

            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(R.color.teal_700),
                contentColor = colorResource(R.color.teal_200))
        ) {
            Text(text = "Change Case", fontSize = 18.sp,fontFamily = FontFamily.SansSerif,)
            Spacer(modifier = Modifier.imePadding())

        }

    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScribdPhase1Theme {
        MainScreen()
    }
}