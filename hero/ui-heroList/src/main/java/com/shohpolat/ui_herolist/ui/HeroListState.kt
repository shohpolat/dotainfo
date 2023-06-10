package com.shohpolat.ui_herolist.ui

import com.shohpolat.core.ProgressBarState
import com.shohpolat.hero_domain.Hero
import com.shohpolat.hero_domain.HeroAttribute
import com.shohpolat.hero_domain.HeroFilter

data class HeroListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val heros:List<Hero> = listOf(),
    val filteredHeros:List<Hero> = listOf(),
    val heroName:String= "",
    val heroFilter:HeroFilter = HeroFilter.Hero(),
    val primaryAttribute: HeroAttribute = HeroAttribute.Unknown
)
