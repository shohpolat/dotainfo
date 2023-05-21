package com.shohpolat.hero_domain

sealed class HeroAttackType(
    val uiValue:String
) {

    object Melee: HeroAttackType(
        uiValue = "Meele"
    )

    object Ranged: HeroAttackType(
        uiValue = "Ranges"
    )

    object Unknown: HeroAttackType(
        uiValue = "Unknown"
    )

}

fun getHeroAttackType(uiValue: String): HeroAttackType{
    return when(uiValue){
        HeroAttackType.Melee.uiValue -> {
            HeroAttackType.Melee
        }
        HeroAttackType.Ranged.uiValue -> {
            HeroAttackType.Ranged
        }
        else -> {
            HeroAttackType.Unknown
        }
    }
}
