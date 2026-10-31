package com.makeevrserg.empireprojekt.mobile.core.ui.button.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable

@Composable
fun AstraIconButtonComposable(
    imageVector: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.onPrimary,
    contentDescription: String? = null,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}

@Composable
fun AstraBackButtonComposable(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AstraIconButtonComposable(
        imageVector = Icons.Default.ChevronLeft,
        onClick = onClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun AstraIconButtonPreview() {
    AdaptThemeFadeComposable {
        Row {
            AstraBackButtonComposable(onClick = {})
            AstraIconButtonComposable(imageVector = Icons.Filled.Search, onClick = {})
        }
    }
}
