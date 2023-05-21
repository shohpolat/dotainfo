package com.shohpolat.hero_interactors

import com.shohpolat.hero_datasource.network.HeroService

data class HeroInteractors(
    val getHeros: GetHeros
) {
    companion object Factory{
        fun build(): HeroInteractors {
            val heroService = HeroService.build()
            return HeroInteractors(
                getHeros = GetHeros(
                    service = heroService
                )
            )
        }
    }
}