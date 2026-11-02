package com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.component

import com.makeevrserg.empireprojekt.mobile.wear.messenger.api.status.model.WearStatusState
import kotlinx.coroutines.flow.StateFlow

interface WearStatusComponent {
    val mergedState: StateFlow<WearStatusState>
}
