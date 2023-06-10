package com.shohpolat.hero_domain

import com.shohpolat.core.FilterOrder

sealed class HeroFilter(val name:String){

    data class Hero(val order:FilterOrder = FilterOrder.Descending): HeroFilter("Hero")

    data class ProWins(val order: FilterOrder = FilterOrder.Descending): HeroFilter("Pro win-rate")

}
