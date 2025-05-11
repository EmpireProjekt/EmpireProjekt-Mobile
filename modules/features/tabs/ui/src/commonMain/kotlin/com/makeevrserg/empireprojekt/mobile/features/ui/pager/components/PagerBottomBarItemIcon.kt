package com.makeevrserg.empireprojekt.mobile.features.ui.pager.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.NetworkWifi
import androidx.compose.material.icons.filled.People
import androidx.compose.ui.graphics.vector.ImageVector
import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.RawStringDesc
import dev.icerock.moko.resources.desc.StringDesc

val PagerBottomBarItem.icon: ImageVector
    get() = when (this) {
        PagerBottomBarItem.Towns -> Icons.Filled.Apartment
        PagerBottomBarItem.Status -> Icons.Filled.NetworkWifi
        PagerBottomBarItem.Ratings -> Icons.Filled.People
        PagerBottomBarItem.Map -> Icons.Filled.Map
    }

// todo
val PagerBottomBarItem.text: RawStringDesc
    get() = when (this) {
        PagerBottomBarItem.Towns -> StringDesc.Raw("TOWNS")
        PagerBottomBarItem.Status -> StringDesc.Raw("STATUSES")
        PagerBottomBarItem.Ratings -> StringDesc.Raw("RATINGS")
        PagerBottomBarItem.Map -> StringDesc.Raw("MAP")
    }
