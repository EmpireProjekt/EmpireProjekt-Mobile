package com.makeevrserg.empireprojekt.mobile.core.ui.paging

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.placeholder.AstraLoading
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

object PagingWidget {
    @Suppress("ModifierMissing")
    @Composable
    fun Base(text: String) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = MR.images.ic_splash.asPainter(),
                modifier = Modifier.size(96.dp),
                contentDescription = null
            )
            Text(
                text = text,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Center,
                fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
            )
        }
    }

    @Composable
    fun LastPage() {
        Base(MR.strings.paging_last_page.asComposableString())
    }

    @Composable
    fun NoPages() {
        Base(MR.strings.paging_no_pages.asComposableString())
    }

    @Composable
    fun NetworkError(onReload: () -> Unit) {
        Column {
            Base(MR.strings.paging_network_error.asComposableString())
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = onReload) {
                    Text(
                        text = MR.strings.paging_reload.asComposableString(),
                        modifier = Modifier.clickable { onReload.invoke() },
                        style = MaterialTheme.typography.h6,
                        color = AppTheme.astraColors.astraLogo.astraOrange,
                        textAlign = TextAlign.Center,
                        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
                    )
                }
            }
        }
    }

    @Suppress("ModifierMissing")
    @Composable
    fun Loading() {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AstraLoading(AppTheme.dimens.M)
        }
    }

    @Composable
    fun Auto(
        list: List<*>,
        isLastPage: Boolean,
        isLoading: Boolean,
        isFailure: Boolean,
        onReload: () -> Unit,
        loader: @Composable () -> Unit = { Loading() }
    ) {
        Crossfade(
            targetState = isLoading,
            label = "crossfade loading indicator",
            content = { isLoading ->
                if (isLoading) {
                    loader.invoke()
                }
            }
        )

        if (isLastPage && list.isNotEmpty()) {
            LastPage()
        } else if (isLastPage && list.isEmpty()) {
            NoPages()
        } else if (isFailure) {
            NetworkError(onReload)
        }
    }
}
