package com.makeevrserg.empireprojekt.mobile.features.theme.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.PreviewThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.component.ThemeSwitcherComponent
import com.makeevrserg.empireprojekt.mobile.features.theme.api.model.Theme

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
    AdaptThemeFadeComposable(composeTheme) {
        content.invoke()
    }
}
