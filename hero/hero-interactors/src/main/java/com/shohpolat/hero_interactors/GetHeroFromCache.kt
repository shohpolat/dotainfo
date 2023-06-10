package com.shohpolat.hero_interactors

import com.shohpolat.core.DataState
import com.shohpolat.core.ProgressBarState
import com.shohpolat.core.UIComponent
import com.shohpolat.hero_datasource.cache.HeroCache
import com.shohpolat.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetHeroFromCache(
    private val cache: HeroCache
) {

    fun execute(
        id:Int
    ): Flow<DataState<Hero>> = flow {
        try {

            emit(DataState.Loading(progressBar = ProgressBarState.Loading))

            val cachedHero = cache.getHero(id) ?: throw Exception("Hero does not exist in Cache")

            emit(DataState.Data(cachedHero))

        }catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Response<Hero>(
                component = UIComponent.Dialog(
                    title = "Error",
                    description = e.message.toString()
                )
            ))
        }
        finally {
            emit(DataState.Loading(progressBar = ProgressBarState.Idle))
        }
    }

}