package com.makeevrserg.empireprojekt.mobile.core.ui.sheet.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.composables.core.BottomSheetScope
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.ModalBottomSheetScope
import com.composables.core.ModalBottomSheetState
import com.composables.core.Scrim
import com.composables.core.Sheet
import com.composables.core.SheetDetent
import com.composables.core.rememberModalBottomSheetState
import com.makeevrserg.empireprojekt.mobile.core.ui.sheet.model.SheetTransition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retryWhen

private val EmptyContent: @Composable ModalBottomSheetScope.() -> Unit = {
    BModalBottomSheetContentComposable { Box(Modifier) }
}

@Composable
private fun ModalBottomSheetScope.BModalBottomSheetContentComposable(
    modifier: Modifier = Modifier,
    background: Color = Color(color = 0xFF1E1E1E),
    horizontalPadding: Dp = 24.dp,
    content: @Composable BottomSheetScope.() -> Unit
) {
    Scrim()
    Sheet(
        modifier = modifier
            .padding(top = 12.dp)
            .statusBarsPadding()
            .padding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues()
            )
            .shadow(4.dp, RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(background)
            .padding(horizontal = horizontalPadding)
            .fillMaxWidth()
            .imePadding(),
    ) {
        Column {
            DragIndication()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center,
                content = { BSheetDragIndicatorComposable() }
            )
            content.invoke(this@Sheet)
        }
    }
}

private suspend fun <T> withRetry(block: suspend () -> T): T {
    return flow { emit(block.invoke()) }
        .retryWhen { _, _ -> true }
        .first()
}

private fun <C : Any> ModalBottomSheetState.selectTransition(instance: C?): SheetTransition<C> {
    if (instance == null) {
        val isExpanded = currentDetent == SheetDetent.FullyExpanded
        return if (isExpanded) SheetTransition.Close else SheetTransition.Unhandled
    }
    if (currentDetent != SheetDetent.Hidden) return SheetTransition.Unhandled
    val isExpanding = targetDetent == SheetDetent.FullyExpanded
    return if (isExpanding) SheetTransition.Open(instance) else SheetTransition.CloseAndOpen(instance)
}

private fun <C : Any> ModalBottomSheetState.buildTransitionFlow(instance: C?): Flow<SheetTransition<C>> {
    return snapshotFlow { selectTransition(instance) }
}

private fun ModalBottomSheetState.isFullyHidden(): Boolean {
    val isTargetHidden = targetDetent == SheetDetent.Hidden
    val isCurrentHidden = currentDetent == SheetDetent.Hidden
    return isTargetHidden && isCurrentHidden && isIdle
}

private suspend fun <C : Any> ModalBottomSheetState.applyTransition(
    transition: SheetTransition<C>,
    childContent: MutableState<@Composable ModalBottomSheetScope.() -> Unit>,
    content: @Composable ModalBottomSheetScope.(C) -> Unit
) {
    when (transition) {
        SheetTransition.Close -> {
            withRetry { animateTo(SheetDetent.Hidden) }
            childContent.value = EmptyContent
            jumpTo(SheetDetent.Hidden)
        }

        is SheetTransition.CloseAndOpen<C> -> {
            withRetry {
                animateTo(SheetDetent.Hidden)
                animateTo(SheetDetent.FullyExpanded)
            }
            childContent.value = { content.invoke(this, transition.instance) }
        }

        is SheetTransition.Open<C> -> {
            animateTo(SheetDetent.FullyExpanded)
            childContent.value = { content.invoke(this, transition.instance) }
        }

        SheetTransition.Unhandled -> Unit
    }
}

@Composable
fun <C : Any> ModalBottomSheetSlotComposable(
    instance: C?,
    onDismiss: () -> Unit,
    initialDetent: SheetDetent = SheetDetent.Hidden,
    content: @Composable ModalBottomSheetScope.(C) -> Unit
) {
    val childContent = remember { mutableStateOf(EmptyContent) }
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentContent by rememberUpdatedState(content)

    val modalSheetState = rememberModalBottomSheetState(
        initialDetent = initialDetent
    )

    LaunchedEffect(modalSheetState) {
        snapshotFlow { modalSheetState.isFullyHidden() }
            .distinctUntilChanged()
            .drop(1)
            .collect { isHidden ->
                if (!isHidden) return@collect
                currentOnDismiss.invoke()
            }
    }
    LaunchedEffect(
        instance,
        modalSheetState,
        childContent.value
    ) {
        modalSheetState.buildTransitionFlow(instance)
            .onEach { transition ->
                modalSheetState.applyTransition(
                    transition = transition,
                    childContent = childContent,
                    content = currentContent
                )
            }
            .collect()
    }
    ModalBottomSheet(
        state = modalSheetState,
        content = { childContent.value.invoke(this) }
    )
}

@Composable
fun <C : Any, T : Any> ModalBottomSheetSlotComposable(
    slot: ChildSlot<C, T>,
    onDismiss: () -> Unit,
    initialDetent: SheetDetent = SheetDetent.Hidden,
    content: @Composable ModalBottomSheetScope.(T) -> Unit
) {
    ModalBottomSheetSlotComposable(
        initialDetent = initialDetent,
        instance = slot.child?.instance,
        onDismiss = onDismiss,
        content = content
    )
}

@Composable
fun <C : Any, T : Any> ModalBottomSheetSlotComposable(
    slot: Value<ChildSlot<C, T>>,
    onDismiss: () -> Unit,
    initialDetent: SheetDetent = SheetDetent.Hidden,
    content: @Composable ModalBottomSheetScope.(T) -> Unit
) {
    ModalBottomSheetSlotComposable(
        initialDetent = initialDetent,
        instance = slot.subscribeAsState().value.child?.instance,
        onDismiss = onDismiss,
        content = content
    )
}
