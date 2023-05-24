package com.shohpolat.ui_herolist.di

import com.shohpolat.hero_interactors.GetHeros
import com.shohpolat.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroListModule {

    @Provides
    @Singleton
    fun provideGetHeros(interactors: HeroInteractors): GetHeros {
        return interactors.getHeros
    }

}