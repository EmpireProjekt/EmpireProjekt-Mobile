package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = { AstraLoading(size = AppTheme.dimens.M) }
    )
}

@Preview
@Composable
private fun LoadingContentPreview() {
    AdaptThemeFade {
        LoadingContent(Modifier.fillMaxSize())
    }
}
