package com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.krate

import com.makeevrserg.empireprojekt.mobile.features.root.api.pager.model.PagerBottomBarItem
import com.makeevrserg.empireprojekt.mobile.features.root.pager.impl.api.LastBottomItemRepository
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.astrainteractive.klibs.kstorage.api.asStateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

internal class KrateLastBottomItemRepository(settings: Settings) : LastBottomItemRepository {

    override val lastBottomItemIndex = DefaultMutableKrate(
        factory = { PagerBottomBarItem.Menu },
        saver = { item -> settings[KEY] = item.ordinal },
        loader = {
            PagerBottomBarItem.entries.getOrElse(
                index = settings[KEY] ?: 0,
                defaultValue = { PagerBottomBarItem.Menu }
            )
        }
    ).asStateFlowMutableKrate()

    private companion object {
        const val KEY = "LAST_BOTTOM"
    }
}
