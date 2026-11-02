package com.makeevrserg.empireprojekt.mobile.features.theme.api.component

import com.makeevrserg.empireprojekt.mobile.features.theme.api.model.Theme
import kotlinx.coroutines.flow.StateFlow

interface ThemeSwitcherComponent {
    val theme: StateFlow<Theme>

    fun selectDarkTheme()
    fun selectLightTheme()
    fun selectTheme(theme: Theme)
    fun next()
}
