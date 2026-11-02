package com.makeevrserg.empireprojekt.mobile.core.ui.sheet.sheet.model

internal sealed interface SheetTransition<out C> {
    data object Close : SheetTransition<Nothing>

    data class Open<C>(val instance: C) : SheetTransition<C>

    data class CloseAndOpen<C>(val instance: C) : SheetTransition<C>

    data object Unhandled : SheetTransition<Nothing>
}
