package com.shohpolat.ui_herolist.ui

import com.shohpolat.core.ProgressBarState
import com.shohpolat.hero_domain.Hero

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros:List<Hero> = listOf()
)
