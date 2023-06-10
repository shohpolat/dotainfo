package com.shohpolat.hero_interactors

import app.cash.sqldelight.db.SqlDriver
import com.shohpolat.hero_datasource.cache.HeroCache
import com.shohpolat.hero_datasource.network.HeroService

data class HeroInteractors(
    val getHeros: GetHeros,
    val getHeroFromCache: GetHeroFromCache,
    val filterHeros: FilterHeros
) {
    companion object Factory{
        fun build(sqlDriver: SqlDriver): HeroInteractors {
            val heroService = HeroService.build()
            val cache = HeroCache.build(sqlDriver)
            return HeroInteractors(
                getHeros = GetHeros(
                    service = heroService,
                    cache = cache
                ),
                getHeroFromCache = GetHeroFromCache(
                    cache = cache
                ),
                filterHeros = FilterHeros()
            )
        }

        val schema = HeroCache.schema

        val dbName: String = HeroCache.dbName
    }
}