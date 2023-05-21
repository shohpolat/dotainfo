package com.shohpolat.core

sealed class ProgressBarState{
    object Loading: ProgressBarState()
    object Idle: ProgressBarState()
}
