package com.shohpolat.hero_datasource.network

import com.shohpolat.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.request.*

class HeroServiceImpl(
    private val httpsClient: HttpClient
):HeroService {
    override suspend fun getHeroStats(): List<Hero> {
         return httpsClient.get<List<HeroDto>>{
             url(EndPoints.HERO_STATS)
         }.map { it.toHero() }
    }
}