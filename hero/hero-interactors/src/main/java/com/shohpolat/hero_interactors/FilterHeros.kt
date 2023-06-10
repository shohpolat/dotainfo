package com.shohpolat.hero_interactors

import com.shohpolat.core.FilterOrder
import com.shohpolat.hero_domain.Hero
import com.shohpolat.hero_domain.HeroAttribute
import com.shohpolat.hero_domain.HeroFilter
import kotlin.math.round

class FilterHeros {

    fun execute(
        current:List<Hero>,
        heroName:String,
        heroFilter: HeroFilter,
        attributeFilter:HeroAttribute
        ) : List<Hero>
    {
        var filteredHeros:MutableList<Hero> = current.filter {
            it.localizedName.lowercase().contains(heroName.lowercase())
        }.toMutableList()

        when(heroFilter) {
            is HeroFilter.Hero -> {
                when(heroFilter.order) {
                    FilterOrder.Ascending -> {
                        filteredHeros.sortBy { it.localizedName }
                    }
                    FilterOrder.Descending -> {
                        filteredHeros.sortByDescending { it.localizedName }
                    }
                }
            }
            is HeroFilter.ProWins -> {
                when(heroFilter.order) {
                    FilterOrder.Ascending -> {
                        filteredHeros.sortBy {
                            getWinrate(it.proPick,it.proWins)
                        }
                    }
                    FilterOrder.Descending -> {
                        filteredHeros.sortByDescending {
                            getWinrate(it.proPick,it.proWins)
                        }
                    }
                }
            }
        }

        when(attributeFilter) {
            is HeroAttribute.Strength -> {
                filteredHeros = filteredHeros.filter { it.primaryAttribute is HeroAttribute.Strength  }.toMutableList()
            }
            is HeroAttribute.Agility -> {
                filteredHeros = filteredHeros.filter { it.primaryAttribute is HeroAttribute.Agility  }.toMutableList()
            }
            is HeroAttribute.Intelligence -> {
                filteredHeros = filteredHeros.filter { it.primaryAttribute is HeroAttribute.Intelligence  }.toMutableList()
            }
            is HeroAttribute.Unknown -> {}
        }

        return filteredHeros

    }

    private fun getWinrate(proPick:Int,proWins:Int):Int {
        return if (proPick < 0) {
            0
        } else {
            val winRate = round(proWins.toDouble() / proPick.toDouble() * 100).toInt()
            winRate
        }
    }

}