package com.shohpolat.ui_herolist.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shohpolat.core.DataState
import com.shohpolat.core.Logger
import com.shohpolat.core.UIComponent
import com.shohpolat.hero_interactors.GetHeros
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel
@Inject
constructor(
    private val getHeros: GetHeros
): ViewModel(){

    private val logger = Logger("GetHerosTest")
    val heros: MutableState<HeroListState> = mutableStateOf(HeroListState())

    init {
        getHeros()
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
                }
                is DataState.Loading -> {
                    heros.value = heros.value.copy(progressBarState = dataState.progressBar)
                }
            }
        }.launchIn(viewModelScope)
    }

}