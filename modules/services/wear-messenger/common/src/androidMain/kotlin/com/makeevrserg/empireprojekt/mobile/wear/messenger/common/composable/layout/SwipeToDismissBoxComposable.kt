package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.composable.layout

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

private fun Any.buildKey(): String = "${this::class.simpleName}_${hashCode().toString(radix = 36)}"

private fun ChildStack<*, *>.resolveConfigurationKeys(): Set<String> {
    return items.mapTo(HashSet()) { item -> item.configuration.buildKey() }
}

@Composable
private fun RetainStatesComposable(
    holder: SaveableStateHolder,
    stack: ChildStack<*, *>
) {
    val currentKeys = stack.resolveConfigurationKeys()
    val knownKeys = remember(holder) { HashSet(currentKeys) }

    DisposableEffect(
        holder,
        currentKeys
    ) {
        knownKeys.forEach { key ->
            if (key !in currentKeys) holder.removeState(key)
        }
        knownKeys.clear()
        knownKeys.addAll(currentKeys)

        onDispose {}
    }
}

@Composable
private fun <C : Any, T : Any> SwipeToDismissBoxComposable(
    stack: ChildStack<C, T>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (child: Child.Created<C, T>) -> Unit
) {
    val active: Child.Created<C, T> = stack.active
    val background: Child.Created<C, T>? = stack.backStack.lastOrNull()
    val holder = rememberSaveableStateHolder()

    RetainStatesComposable(holder, stack)

    SwipeToDismissBox(
        onDismissed = onDismiss,
        modifier = modifier,
        backgroundKey = background?.configuration ?: SwipeToDismissKeys.Background,
        contentKey = active.configuration,
        hasBackground = background != null
    ) { isBackground ->
        val child = background?.takeIf { isBackground } ?: active
        holder.SaveableStateProvider(child.configuration.buildKey()) {
            content.invoke(child)
        }
    }
}

@Composable
fun <C : Any, T : Any> SwipeToDismissBoxComposable(
    stack: Value<ChildStack<C, T>>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (child: Child.Created<C, T>) -> Unit
) {
    val state = stack.subscribeAsState()

    SwipeToDismissBoxComposable(
        stack = state.value,
        onDismiss = onDismiss,
        modifier = modifier,
        content = content
    )
}
