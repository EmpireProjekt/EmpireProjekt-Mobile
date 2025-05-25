package com.makeevrserg.empireprojekt.mobile.features.ui.pager.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem

@Composable
private fun AstraBottomNavItem(
    icon: Painter,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val tint by animateColorAsState(
        targetValue = when {
            isSelected -> AppTheme.astraColors.surface.onSecondaryVariant
            else -> MaterialTheme.colors.onSecondary
        },
        label = ""
    )
    val background by animateColorAsState(
        targetValue = when {
            isSelected -> MaterialTheme.colors.secondaryVariant
            else -> Color.Transparent
        },
        label = "selected tint color"
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .background(background)
            .clickable(onClick = onClick)
            .padding(AppTheme.dimens.XS)
            .animateContentSize()

    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = tint
        )
    }
}

@Composable
internal fun PagerBottomBar(
    selectedIndex: Int,
    onClicked: (PagerBottomBarItem) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = remember {
        listOf(
            PagerBottomBarItem.Towns,
            PagerBottomBarItem.Status,
            PagerBottomBarItem.Ratings,
            PagerBottomBarItem.Map
        )
    }
    Row(
        modifier
            .fillMaxWidth()
            .clickable(enabled = false, onClick = {})
            .wrapContentHeight()
            .padding(horizontal = AppTheme.dimens.S)
            .padding(vertical = AppTheme.dimens.XS)
            .clip(RoundedCornerShape(AppTheme.dimens.S))
            .border(2.dp, MaterialTheme.colors.primaryVariant, RoundedCornerShape(AppTheme.dimens.S))
            .background(MaterialTheme.colors.primary)
            .padding(vertical = AppTheme.dimens.S)
            .padding(horizontal = AppTheme.dimens.S),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEachIndexed { index, item ->
            AstraBottomNavItem(
                icon = rememberVectorPainter(item.icon),
                isSelected = selectedIndex == index,
                onClick = { onClicked.invoke(item) }
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
private fun PagerBottomBarPreview() {
    AdaptThemeFade {
        Scaffold(
            bottomBar = {
                PagerBottomBar(
                    selectedIndex = 1,
                    onClicked = {
                    }
                )
            }
        ) {
        }
    }
}
