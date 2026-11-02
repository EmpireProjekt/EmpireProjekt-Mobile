package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util

import androidx.wear.protolayout.ColorBuilders.ColorProp

internal val Int.asColorProp: ColorProp
    get() = ColorProp.Builder()
        .setArgb(this)
        .build()
