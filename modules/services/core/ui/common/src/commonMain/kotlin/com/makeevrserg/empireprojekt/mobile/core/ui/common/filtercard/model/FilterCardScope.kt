package com.makeevrserg.empireprojekt.mobile.core.ui.common.filtercard.model

import androidx.compose.foundation.layout.ColumnScope

interface FilterCardScope : ColumnScope {
    class Default(columnScope: ColumnScope) : FilterCardScope, ColumnScope by columnScope
}
