package com.shohpolat.ui_herodetail.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun HeroDetail(
    heroId:Int?
) {
    Text(text = heroId.toString())
}