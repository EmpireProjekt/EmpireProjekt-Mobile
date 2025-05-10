package com.makeevrserg.empireprojekt.mobile.core.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value

@Composable
fun <C : Any> ModalDialogSlot(
    instance: C?,
    onDismiss: () -> Unit,
    content: @Composable (C) -> Unit
) {
    instance?.let { instance ->
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            ),
            content = {
                content.invoke(instance)
            }
        )
    }
}

@Composable
fun <C : Any, T : Any> ModalDialogSlot(
    slot: Value<ChildSlot<C, T>>,
    onDismiss: () -> Unit,
    content: @Composable (T) -> Unit
) {
    ModalDialogSlot(
        instance = slot.subscribeAsState().value.child?.instance,
        onDismiss = onDismiss,
        content = content
    )
}
