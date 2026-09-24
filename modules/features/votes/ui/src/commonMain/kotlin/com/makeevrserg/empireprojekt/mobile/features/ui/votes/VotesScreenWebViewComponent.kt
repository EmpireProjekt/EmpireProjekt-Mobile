package com.makeevrserg.empireprojekt.mobile.features.ui.votes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.webview.AstraWebView

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
            AstraCenterAlignedTopAppBar(onBackClick = onPop)
        }
    ) { _ ->
        AstraWebView(
            url = url,
            modifier = Modifier.fillMaxSize()
        )
    }
}
