package com.shohpolat.core

sealed class FilterOrder{

    object Ascending: FilterOrder()

    object Descending: FilterOrder()

}
