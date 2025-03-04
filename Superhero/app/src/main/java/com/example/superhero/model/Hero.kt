package com.example.superhero.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Hero (
    @StringRes val name: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
) {

}