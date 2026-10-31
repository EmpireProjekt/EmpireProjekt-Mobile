package com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.api

import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.model.PagerBottomBarItem
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate

internal interface LastBottomItemRepository {
    val lastBottomItemIndex: StateFlowMutableKrate<PagerBottomBarItem>
}
