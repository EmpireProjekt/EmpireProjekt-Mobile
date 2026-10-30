package com.makeevrserg.empireprojekt.mobile.core.ui.decompose.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

typealias DecomposeScreen<T> = @Composable (modifier: Modifier, child: T) -> Unit
