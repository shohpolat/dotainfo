package com.shohpolat.hero_interactors

import com.shohpolat.core.DataState
import com.shohpolat.core.ProgressBarState
import com.shohpolat.core.UIComponent
import com.shohpolat.hero_datasource.network.HeroService
import com.shohpolat.hero_domain.Hero
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.xml.crypto.Data

class GetHeros(
    private val service: HeroService
) {

    fun execute(): Flow<DataState<List<Hero>>> = flow {

        try {

            emit(DataState.Loading(progressBar = ProgressBarState.Loading))

            val herosList:List<Hero> = try {
                service.getHeroStats()
            }catch (e:Exception) {
                emit(
                    DataState.Response(
                        component = UIComponent.Dialog(
                            title = "Network Data Error",
                            description = e.message ?: "Unknown error"
                        )
                    )
                )
                listOf()
            }

        }catch (e:Exception) {
            e.printStackTrace()
            emit(
                DataState.Response(
                    component = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Unknown error"
                    )
                )
            )
        }
        finally {
            emit(DataState.Loading(progressBar = ProgressBarState.Idle))
        }

    }

}