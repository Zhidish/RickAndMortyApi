package com.leobit.testapplication.morty_menu.model

import androidx.annotation.DrawableRes

data class MenuItem(
    @DrawableRes val image: Int,
    val name: String
) {

}