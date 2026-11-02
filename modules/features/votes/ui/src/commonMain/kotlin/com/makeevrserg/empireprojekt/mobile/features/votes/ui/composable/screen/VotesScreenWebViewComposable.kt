package com.makeevrserg.empireprojekt.mobile.features.votes.ui.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.common.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.webview.composable.AstraWebViewComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme

@Suppress("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VotesScreenWebViewComposable(
    url: String,
    onPop: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = AppTheme.dimens.XS),
        topBar = {
            AstraCenterAlignedTopAppBarComposable(onBackClick = onPop)
        }
    ) { _ ->
        AstraWebViewComposable(
            url = url,
            modifier = Modifier.fillMaxSize()
        )
    }
}
