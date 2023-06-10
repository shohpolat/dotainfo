package com.shohpolat.ui_herodetail.ui

sealed class HeroDetailEvents {

    data class GetHeroFromCache(val heroId:Int): HeroDetailEvents()

}