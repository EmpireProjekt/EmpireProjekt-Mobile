package com.makeevrserg.empireprojekt.mobile.wear.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.SaveableStateHolder
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.SwipeToDismissBox
import androidx.wear.compose.material.SwipeToDismissKeys
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

// Copy from https://github.com/joreilly/Confetti

@Composable
fun <C : Any, T : Any> SwipeToDismissBox(
    stack: Value<ChildStack<C, T>>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (child: Child.Created<C, T>) -> Unit,
) {
    val state = stack.subscribeAsState()

    SwipeToDismissBox(
        stack = state.value,
        onDismiss = onDismiss,
        modifier = modifier,
        content = content
    )
}

@Composable
fun <C : Any, T : Any> SwipeToDismissBox(
    stack: ChildStack<C, T>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (child: Child.Created<C, T>) -> Unit,
) {
    val active: Child.Created<C, T> = stack.active
    val background: Child.Created<C, T>? = stack.backStack.lastOrNull()
    val holder = rememberSaveableStateHolder()

    RetainStates(holder, stack.getConfigurations())

    SwipeToDismissBox(
        onDismissed = onDismiss,
        modifier = modifier,
        backgroundKey = background?.configuration ?: SwipeToDismissKeys.Background,
        contentKey = active.configuration,
        hasBackground = background != null,
    ) { isBackground ->
        val child = background?.takeIf { isBackground } ?: active
        holder.SaveableStateProvider(child.configuration.key()) {
            content(child)
        }
    }
}

private fun ChildStack<*, *>.getConfigurations(): Set<String> =
    items.mapTo(HashSet()) { it.configuration.key() }

private fun Any.key(): String = "${this::class.simpleName}_${hashCode().toString(radix = 36)}"

@Suppress("UnstableCollections")
@Composable
private fun RetainStates(
    holder: SaveableStateHolder,
    currentKeys: Set<String>
) {
    val keys = remember(holder) { Keys(currentKeys) }

    DisposableEffect(holder, currentKeys) {
        keys.set.forEach {
            if (it !in currentKeys) {
                holder.removeState(it)
            }
        }

        keys.set = currentKeys

        onDispose {}
    }
}

private class Keys(var set: Set<String>)
