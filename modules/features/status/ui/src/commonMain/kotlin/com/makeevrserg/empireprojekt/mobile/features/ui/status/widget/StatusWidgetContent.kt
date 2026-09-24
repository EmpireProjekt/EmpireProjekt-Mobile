package com.makeevrserg.empireprojekt.mobile.features.ui.status.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.status.url.presentation.UrlStatusComponent
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc

private const val FADE_DURATION = 1200

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun StatusWidgetContent(
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
            .clip(RoundedCornerShape(AppTheme.dimens.XS))
            .background(MaterialTheme.colors.primary)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = (status to isLoading),
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            },
            label = "side color status"
        ) { (animatedStatus, animatedIsLoading) ->
            SideColorStatusWidget(animatedStatus, animatedIsLoading)
        }

        AnimatedContent(
            modifier = Modifier.fillMaxHeight(),
            targetState = status,
            transitionSpec = {
                fadeIn(tween(FADE_DURATION)) with fadeOut(tween(FADE_DURATION))
            },
            label = "status icon"
        ) { animatedStatus ->
            IconWidget(animatedStatus)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AstraText(
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
    AdaptThemeFade {
        StatusWidgetContent(
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
    AdaptThemeFade {
        StatusWidgetContent(
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
    AdaptThemeFade {
        StatusWidgetContent(
            status = UrlStatusComponent.LoadingStatus.LOADING,
            isLoading = true,
            title = StringDesc.Raw("Empire Survival"),
            onClick = {}
        )
    }
}
