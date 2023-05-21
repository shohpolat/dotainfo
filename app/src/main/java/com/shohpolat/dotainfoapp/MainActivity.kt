package com.shohpolat.dotainfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.shohpolat.core.DataState
import com.shohpolat.core.Logger
import com.shohpolat.core.ProgressBarState
import com.shohpolat.core.UIComponent
import com.shohpolat.dotainfoapp.ui.theme.DotaInfoAppTheme
import com.shohpolat.hero_domain.Hero
import com.shohpolat.hero_interactors.HeroInteractors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private val heros:MutableState<List<Hero>> = mutableStateOf(emptyList())
    private val progressBarState:MutableState<ProgressBarState> = mutableStateOf(ProgressBarState.Idle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getHeros = HeroInteractors.build(
            sqlDriver = AndroidSqliteDriver(
                schema = HeroInteractors.schema,
                context = this,
                name = HeroInteractors.dbName
            )
        ).getHeros
        val logger = Logger("GetHerosTest")
        getHeros.execute().onEach { dataState ->
            when(dataState) {
                is DataState.Response -> {
                    when(dataState.component) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.component as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.component as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    heros.value = dataState.data ?: emptyList()
                }
                is DataState.Loading -> {
                    progressBarState.value = dataState.progressBar
                }
            }
        }.launchIn(CoroutineScope(IO))

        setContent {
            DotaInfoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn{
                            items(heros.value) { item: Hero ->
                                Text(text = item.localizedName)
                            }
                        }
                        if (progressBarState.value == ProgressBarState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DotaInfoAppTheme {
        Greeting("Android")
    }
}