package com.shohpolat.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import coil.ImageLoader
import com.shohpolat.dotainfoapp.R
import com.shohpolat.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroInteractorsModule {


    @Provides
    @Singleton
    fun provideImageLoader(app:Application): ImageLoader{
        return ImageLoader.Builder(app)
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .availableMemoryPercentage(.25)
            .crossfade(true)
            .build()
    }


    @Provides
    @Singleton
    @Named("heroAndroidSqlDriver")
    fun provideAndroidDriver(app:Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = HeroInteractors.schema,
            context = app,
            name = HeroInteractors.dbName
        )
    }

    @Provides
    @Singleton
    fun provideHeroInteractors(
        @Named("heroAndroidSqlDriver") sqlDriver: SqlDriver
    ): HeroInteractors {
        return HeroInteractors.build(sqlDriver)
    }

}