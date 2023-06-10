package com.shohpolat.ui_herolist.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.shohpolat.core.ProgressBarState
import com.shohpolat.hero_domain.Hero
import com.shohpolat.ui_herolist.components.HeroFilterSelector
import com.shohpolat.ui_herolist.components.HeroListFilter
import com.shohpolat.ui_herolist.components.HeroListItem
import com.shohpolat.ui_herolist.components.HeroListToolbar

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun HeroList(
    state: HeroListState,
    events:(HeroListEvents) -> Unit,
    imageLoader: ImageLoader,
    navigateToDetailsScreen:(Int) -> Unit
){
    Box(modifier = Modifier.fillMaxSize()) {
        Column {

            HeroListToolbar(
                heroName = state.heroName,
                onHeroNameChanged = {
                    events(HeroListEvents.UpdateHeroName(heroName = it))
                },
                onExecuteSearch = {
                    events(HeroListEvents.FilterHeros)
                },
                onShowFilterDialog = { }
            )

            LazyColumn{
                items(state.filteredHeros) { item: Hero ->
                    HeroListItem(
                        hero = item,
                        onSelectHero = { heroId->
                            navigateToDetailsScreen(heroId)
                        },
                        imageLoader = imageLoader
                    )
                }
            }
        }
        HeroListFilter(
            heroFilter = state.heroFilter ,
            onUpdateHeroFilter = {
                events(HeroListEvents.UpdateHeroFilter(it))
            },
            onCloseDialog = {}
        )

        if (state.progressBarState == ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}