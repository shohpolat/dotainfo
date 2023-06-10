package com.shohpolat.ui_herodetail.di

import com.shohpolat.hero_interactors.GetHeroFromCache
import com.shohpolat.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroDetailModule {

    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        heroInteractors: HeroInteractors
    ): GetHeroFromCache {
        return heroInteractors.getHeroFromCache
    }

}