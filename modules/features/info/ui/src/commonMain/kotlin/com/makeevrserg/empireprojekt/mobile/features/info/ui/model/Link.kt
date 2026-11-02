package com.makeevrserg.empireprojekt.mobile.features.info.ui.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.ImageResource

internal class Link(
    val res: ImageResource,
    val title: String,
    val url: String,
    val tint: @Composable () -> Color = { Color.Unspecified }
)
