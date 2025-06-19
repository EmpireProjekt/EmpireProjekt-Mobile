package com.makeevrserg.empireprojekt.mobile.features.root.pager.data

import com.makeevrserg.empireprojekt.mobile.features.root.pager.model.PagerBottomBarItem
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate
import ru.astrainteractive.klibs.kstorage.util.asStateFlowMutableKrate

interface LastBottomItemRepository {
    val lastBottomItemIndex: StateFlowMutableKrate<PagerBottomBarItem>
}

class LastBottomItemRepositoryImpl(private val settings: Settings) : LastBottomItemRepository {
    @Suppress("VariableNaming")
    private val KEY = "LAST_BOTTOM"

    override val lastBottomItemIndex = DefaultMutableKrate(
        factory = { PagerBottomBarItem.Menu },
        saver = { settings[KEY] = it.ordinal },
        loader = {
            PagerBottomBarItem.entries.getOrElse(
                index = settings[KEY] ?: 0,
                defaultValue = { PagerBottomBarItem.Menu }
            )
        }
    ).asStateFlowMutableKrate()
}
