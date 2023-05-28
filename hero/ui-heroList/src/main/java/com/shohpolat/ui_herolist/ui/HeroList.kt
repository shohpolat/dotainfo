package com.shohpolat.ui_herolist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.shohpolat.core.ProgressBarState
import com.shohpolat.hero_domain.Hero
import com.shohpolat.ui_herolist.components.HeroListItem

@Composable
fun HeroList(
    state: HeroListState,
    imageLoader: ImageLoader,
    navigateToDetailsScreen:(Int) -> Unit
){
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn{
            items(state.heros) { item: Hero ->
                HeroListItem(
                    hero = item,
                    onSelectHero = { heroId->
                        navigateToDetailsScreen(heroId)
                    },
                    imageLoader = imageLoader
                )
            }
        }
        if (state.progressBarState == ProgressBarState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}