package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.model

import androidx.compose.foundation.layout.ColumnScope

interface FilterCardScope : ColumnScope {
    class Default(columnScope: ColumnScope) : FilterCardScope, ColumnScope by columnScope
}
