package com.makeevrserg.empireprojekt.mobile.core.ui.common

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Modifier

actual fun Modifier.navBarsPadding(): Modifier {
    return then(navigationBarsPadding())
}
