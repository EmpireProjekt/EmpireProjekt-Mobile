package com.makeevrserg.empireprojekt.mobile.features.theme.impl.krate

import com.makeevrserg.empireprojekt.mobile.features.theme.api.data.model.Theme
import com.makeevrserg.empireprojekt.mobile.features.theme.impl.api.ThemeSwitcherRepository
import com.russhwolf.settings.Settings
import ru.astrainteractive.klibs.kstorage.api.asStateFlowMutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate
import ru.astrainteractive.klibs.kstorage.api.value.ValueFactory

internal class KrateThemeSwitcherRepository(
    settings: Settings
) : ThemeSwitcherRepository {

    private val themeValueFactory = ValueFactory { Theme.DARK }

    override val themeFlowStorageValue = DefaultMutableKrate(
        factory = themeValueFactory,
        loader = {
            val ordinal = settings.getInt(KEY, Theme.LIGHT.ordinal)
            Theme.entries.getOrNull(ordinal) ?: themeValueFactory.create()
        },
        saver = { theme ->
            settings.putInt(KEY, theme.ordinal)
        }
    ).asStateFlowMutableKrate()

    private companion object {
        const val KEY = "THEME"
    }
}
