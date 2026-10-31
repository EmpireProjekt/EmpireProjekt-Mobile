package com.makeevrserg.empireprojekt.mobile.features.ui.status.composable.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.astraCard
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.ui.status.model.StatusWidgetState
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlin.time.Duration.Companion.milliseconds

private val FadeDuration = 1200.milliseconds

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun StatusWidgetContentComposable(
    status: UrlStatusComponent.LoadingStatus,
    isLoading: Boolean,
    title: StringDesc,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(vertical = AppTheme.dimens.S)
            .height(54.dp)
            .fillMaxWidth()
            .astraCard(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(AppTheme.dimens.XS)
            )
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = StatusWidgetState(status, isLoading),
            transitionSpec = {
                fadeIn(tween(FadeDuration.inWholeMilliseconds.toInt())) with
                    fadeOut(tween(FadeDuration.inWholeMilliseconds.toInt()))
            },
            label = "side color status"
        ) { (animatedStatus, animatedIsLoading) ->
            SideColorStatusWidgetComposable(animatedStatus, animatedIsLoading)
        }

        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = status,
            transitionSpec = {
                fadeIn(tween(FadeDuration.inWholeMilliseconds.toInt())) with
                    fadeOut(tween(FadeDuration.inWholeMilliseconds.toInt()))
            },
            label = "status icon"
        ) { animatedStatus ->
            IconWidgetComposable(status = animatedStatus)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AstraTextComposable(
                text = title.asComposableString(),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Preview
@Composable
private fun StatusWidgetContentSuccessPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContentComposable(
            status = UrlStatusComponent.LoadingStatus.SUCCESS,
            isLoading = false,
            title = StringDesc.Raw("Empire Survival"),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun StatusWidgetContentErrorPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContentComposable(
            status = UrlStatusComponent.LoadingStatus.ERROR,
            isLoading = false,
            title = StringDesc.Raw("Empire Survival"),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun StatusWidgetContentLoadingPreview() {
    AdaptThemeFadeComposable {
        StatusWidgetContentComposable(
            status = UrlStatusComponent.LoadingStatus.LOADING,
            isLoading = true,
            title = StringDesc.Raw("Empire Survival"),
            onClick = {}
        )
    }
}
