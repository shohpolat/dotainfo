package com.shohpolat.hero_datasource.network

import com.shohpolat.hero_domain.Hero
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import kotlinx.serialization.json.Json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

sealed interface HeroService{
    suspend fun getHeroStats():List<Hero>

    companion object Factory {
        fun build():HeroService {
            return HeroServiceImpl(
                httpsClient = HttpClient(Android) {
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(
                            kotlinx.serialization.json.Json{
                                ignoreUnknownKeys = true
                            }
                        )
                    }
                }
            )
        }
    }

}