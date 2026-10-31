package com.makeevrserg.empireprojekt.mobile.features.ui.votes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.composable.AstraWebViewComposable

@Suppress("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VotesScreenWebViewComponent(
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
