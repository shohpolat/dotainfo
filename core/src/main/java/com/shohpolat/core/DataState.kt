package com.shohpolat.core

sealed class DataState<T>{
    data class Response<T>(val component:UIComponent): DataState<T>()
    data class Data<T>(val data:T? = null): DataState<T>()
    data class Loading<T>(val progressBar:ProgressBarState = ProgressBarState.Idle): DataState<T>()
}
