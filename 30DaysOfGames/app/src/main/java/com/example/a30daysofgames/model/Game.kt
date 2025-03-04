package com.example.a30daysofgames.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Game (
    @StringRes val titleResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @StringRes val publishingResourceId: Int,
    @DrawableRes val imageResourceId: Int
) {
}