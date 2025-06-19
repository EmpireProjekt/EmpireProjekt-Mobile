package com.makeevrserg.empireprojekt.mobile.features.ui.pager.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem

val PagerBottomBarItem.icon: ImageVector
    get() = when (this) {
        PagerBottomBarItem.Menu -> Icons.Filled.Apps
        PagerBottomBarItem.Map -> Icons.Filled.Map
    }
