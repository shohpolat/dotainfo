package com.shohpolat.dotainfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import coil.ImageLoader
import com.shohpolat.core.DataState
import com.shohpolat.core.Logger
import com.shohpolat.core.ProgressBarState
import com.shohpolat.core.UIComponent
import com.shohpolat.dotainfoapp.ui.theme.DotaInfoAppTheme
import com.shohpolat.hero_interactors.HeroInteractors
import com.shohpolat.ui_herolist.ui.HeroList
import com.shohpolat.ui_herolist.ui.HeroListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.shohpolat.dotainfoapp.R
import com.shohpolat.ui_herolist.ui.HeroListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader:ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DotaInfoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val heroListViewModel: HeroListViewModel = hiltViewModel()
                   HeroList(
                       state = heroListViewModel.heros.value,
                       imageLoader = imageLoader
                   )
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