package com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme

@Composable
fun LoadingContentComposable(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        content = { AstraLoadingComposable(size = AppTheme.dimens.M) }
    )
}

@Preview
@Composable
private fun LoadingContentPreview() {
    AdaptThemeFadeComposable {
        LoadingContentComposable(Modifier.fillMaxSize())
    }
}
