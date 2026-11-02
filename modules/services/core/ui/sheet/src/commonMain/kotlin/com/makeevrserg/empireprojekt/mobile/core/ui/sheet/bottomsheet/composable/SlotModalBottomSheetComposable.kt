package com.makeevrserg.empireprojekt.mobile.core.ui.sheet.bottomsheet.composable

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import com.arkivanov.decompose.router.slot.ChildSlot
import com.makeevrserg.empireprojekt.mobile.core.ui.sheet.bottomsheet.util.zero
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T : Any, K : Any> SlotModalBottomSheetComposable(
    childSlot: ChildSlot<T, K>,
    onDismiss: () -> Unit,
    skipPartiallyExpanded: Boolean = true,
    content: @Composable ColumnScope.(K) -> Unit
) {
    val child = childSlot.child?.instance
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded,
        confirmValueChange = { true }
    )
    val currentOnDismiss by rememberUpdatedState(onDismiss)

    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }
            .distinctUntilChanged()
            .drop(1)
            .collect { visible ->
                if (visible) return@collect
                currentOnDismiss.invoke()
            }
    }

    if (child != null) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            contentWindowInsets = { WindowInsets.zero },
            content = {
                content.invoke(this, child)
            }
        )
    }
}
