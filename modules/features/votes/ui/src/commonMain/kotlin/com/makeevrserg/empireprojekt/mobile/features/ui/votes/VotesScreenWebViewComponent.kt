package com.makeevrserg.empireprojekt.mobile.features.ui.votes

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.AstraLoading
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.ui.votes.components.LoadingWebViewClient

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VotesScreenWebViewComponent(
    url: String,
    onPop: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = AppTheme.dimens.XS),
        topBar = {
            AstraCenterAlignedTopAppBar(onBackClicked = onPop)
        }
    ) { _ ->
        var webView: WebView? = remember {
            null
        }
        var isLoading by remember {
            mutableStateOf(true)
        }
        Column(modifier = Modifier) {
            AndroidView(
                modifier = Modifier.weight(1f),
                factory = { context ->
                    webView ?: WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.javaScriptEnabled = true
                        settings.useWideViewPort = true
                        settings.allowFileAccess = true
                        settings.allowContentAccess = true
                        settings.builtInZoomControls = true
                        settings.displayZoomControls = false
                        settings.useWideViewPort = true
                        settings.loadWithOverviewMode = true
                        settings.domStorageEnabled = true
                        settings.blockNetworkLoads = false
                        settings.blockNetworkImage = false
                        settings.databaseEnabled = true
                        settings.setSupportZoom(true)
                        scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
                        isScrollbarFadingEnabled = false
                        webViewClient = LoadingWebViewClient { isLoading = it }
                        loadUrl(url)
                    }.also { webView = it }
                },
                update = {
                    webView = it
                }
            )
        }
        Crossfade(
            targetState = isLoading,
            label = "loading indicator crossfade"
        ) { localIsLoading ->
            if (localIsLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AstraLoading(AppTheme.dimens.M)
                }
            }
        }
    }
}
