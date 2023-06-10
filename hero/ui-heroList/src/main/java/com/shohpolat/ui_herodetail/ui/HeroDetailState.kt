package com.shohpolat.ui_herodetail.ui

import com.shohpolat.core.ProgressBarState
import com.shohpolat.hero_domain.Hero

data class HeroDetailState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val hero: Hero?= null
)