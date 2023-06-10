package com.shohpolat.ui_herolist.ui

import com.shohpolat.hero_domain.HeroFilter

sealed class HeroListEvents {

    object GetHeros: HeroListEvents()

    object FilterHeros: HeroListEvents()

    data class UpdateHeroName(val heroName:String): HeroListEvents()

    data class UpdateHeroFilter(val heroFilter: HeroFilter): HeroListEvents()

}