package com.makeevrserg.empireprojekt.mobile.features.theme.data

import com.makeevrserg.empireprojekt.mobile.features.theme.data.model.Theme
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate
import ru.astrainteractive.klibs.kstorage.api.value.ValueFactory
import ru.astrainteractive.klibs.kstorage.util.asStateFlowMutableKrate

internal class ThemeSwitcherRepositoryImpl(
    private val settings: Settings
) : ThemeSwitcherRepository {

    private val key = "THEME"

    private val themeValueFactory = ValueFactory { Theme.DARK }

    override val themeFlowStorageValue = DefaultMutableKrate(
        factory = themeValueFactory,
        loader = {
            val ordinal = settings.getInt(key, Theme.LIGHT.ordinal)
            Theme.entries.getOrNull(ordinal) ?: themeValueFactory.create()
        },
        saver = {
            settings.putInt(key, it.ordinal)
        }
    ).asStateFlowMutableKrate()
}
