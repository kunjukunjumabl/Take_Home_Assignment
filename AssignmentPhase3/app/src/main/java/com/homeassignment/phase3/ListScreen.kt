package com.homeassignment.phase3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homeassignment.phase3.ui.theme.ScribdPhase3Theme

class ListScreen : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myMutableList = mutableStateListOf<String>()
        for (items in 0..99) {
            myMutableList.add("Item $items")
        }
        setContent {
            ScribdPhase3Theme() {
                window.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                Scaffold(topBar = { MainTopBar() }) {
                   LazyColumn {
                       itemsIndexed(
                            items = myMutableList,
                            key = { index, item ->
                                item.hashCode()
                            }
                        ) { index, item ->
                            ItemCard(name = item)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ItemCard(name: String) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
//        shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            backgroundColor = colorResource(R.color.color_on_surface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Column(Modifier.padding(8.dp)) {

                    Text(
                        text = name,
                        color = colorResource(R.color.teal_700),
                        fontSize = 22.sp, fontFamily = FontFamily.SansSerif,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }




    @Composable
    private fun MainTopBar() {
        TopAppBar(title = { Text(text = "Items") })
    }

}