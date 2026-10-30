package com.makeevrserg.empireprojekt.mobile.features.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.component.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.component.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme

@Composable
fun ApplicationTheme(
    themeSwitcherComponent: ThemeSwitcherComponent = PreviewThemeSwitcherComponent(),
    content: @Composable () -> Unit
) {
    val theme by themeSwitcherComponent.theme.collectAsState()
    val composeTheme = when (theme) {
        Theme.DARK -> ComposeTheme.DARK
        Theme.LIGHT -> ComposeTheme.LIGHT
    }
    AdaptThemeFade(composeTheme) {
        content.invoke()
    }
}
