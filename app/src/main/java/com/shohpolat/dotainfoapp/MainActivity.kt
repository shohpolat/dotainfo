package com.shohpolat.dotainfoapp

import android.content.res.Resources
import android.graphics.drawable.Drawable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.res.ResourcesCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.shohpolat.navigation.Screen
import com.shohpolat.ui_herodetail.ui.HeroDetail
import com.shohpolat.ui_herodetail.ui.HeroDetailViewModel
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

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.HeroList.route,
                        builder = {

                            addHeroList(
                                navController = navController,
                                imageLoader = imageLoader
                            )

                            addHeroDetail(
                                imageLoader = imageLoader,
                                resources = resources
                            )

                        }
                    )
                }
            }
        }
    }
}

fun NavGraphBuilder.addHeroList(
    navController: NavController,
    imageLoader: ImageLoader
){
    composable(
        route = Screen.HeroList.route,
    ) {
        val heroListViewModel: HeroListViewModel = hiltViewModel()
        HeroList(
            state = heroListViewModel.heros.value,
            events = heroListViewModel::onEventTriggered,
            imageLoader = imageLoader,
            navigateToDetailsScreen = { heroId ->
                navController.navigate(
                    route = "${Screen.HeroDetail.route}/$heroId"
                )
            }
        )
    }
}

fun NavGraphBuilder.addHeroDetail(
    imageLoader: ImageLoader,
    resources:Resources
) {
    composable(
        route = Screen.HeroDetail.route + "/{heroId}",
        arguments = Screen.HeroDetail.arguments
    ) {
        val viewModel: HeroDetailViewModel = hiltViewModel()
        HeroDetail(
            state = viewModel.state.value,
            imageLoader = imageLoader,
            placeHolderDark = ResourcesCompat.getDrawable(resources,R.drawable.black_background,null),
            placeHolderLight = ResourcesCompat.getDrawable(resources,R.drawable.white_background,null)
        )
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