package com.makeevrserg.empireprojekt.mobile.core.ui.decompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

fun interface DecomposeScreen<T : Any> {
    @Composable
    fun Render(modifier: Modifier, child: T)
}
