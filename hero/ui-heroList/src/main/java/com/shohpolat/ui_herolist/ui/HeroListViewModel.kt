package com.shohpolat.ui_herolist.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohpolat.core.DataState
import com.shohpolat.core.Logger
import com.shohpolat.core.UIComponent
import com.shohpolat.hero_domain.HeroFilter
import com.shohpolat.hero_interactors.FilterHeros
import com.shohpolat.hero_interactors.GetHeros
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HeroListViewModel
@Inject
constructor(
    private val getHeros: GetHeros,
    private @Named("HeroListLogger") val  logger: Logger,
    private val filterHeros: FilterHeros
): ViewModel(){

    val heros: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        onEventTriggered(HeroListEvents.GetHeros)
    }

    fun onEventTriggered(event: HeroListEvents) {
        when(event) {
            is HeroListEvents.GetHeros -> getHeros()
            is HeroListEvents.FilterHeros -> filterHeros()
            is HeroListEvents.UpdateHeroName -> updateHeroName(event.heroName)
            is HeroListEvents.UpdateHeroFilter -> {updateHeroFilter(event.heroFilter)}
        }
    }

    private fun updateHeroFilter(heroFilter: HeroFilter) {
        heros.value = heros.value.copy(heroFilter = heroFilter)
        filterHeros()
    }

    private fun updateHeroName(heroName:String) {
        heros.value = heros.value.copy(heroName = heroName)
    }

    private fun filterHeros() {
        val filteredHeros = filterHeros.execute(
            current = heros.value.heros,
            heroName = heros.value.heroName,
            heroFilter = heros.value.heroFilter,
            attributeFilter = heros.value.primaryAttribute
        )
        heros.value = heros.value.copy(filteredHeros = filteredHeros)
    }

    fun getHeros() {
        getHeros.execute().onEach { dataState ->
            when(dataState) {
                is DataState.Response -> {
                    when(dataState.component) {
                        is UIComponent.Dialog -> {
                            logger.log((dataState.component as UIComponent.Dialog).description)
                        }
                        is UIComponent.None -> {
                            logger.log((dataState.component as UIComponent.None).message)
                        }
                    }
                }
                is DataState.Data -> {
                    heros.value = heros.value.copy(heros = dataState.data ?: listOf())
                    filterHeros()
                }
                is DataState.Loading -> {
                    heros.value = heros.value.copy(progressBarState = dataState.progressBar)
                }
            }
        }.launchIn(viewModelScope)
    }

}