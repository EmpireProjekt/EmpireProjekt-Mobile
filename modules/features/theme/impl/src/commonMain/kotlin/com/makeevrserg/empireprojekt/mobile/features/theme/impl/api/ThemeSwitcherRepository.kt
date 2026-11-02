package com.makeevrserg.empireprojekt.mobile.features.theme.impl.api

import com.makeevrserg.empireprojekt.mobile.features.theme.api.model.Theme
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableKrate

internal interface ThemeSwitcherRepository {

    val themeFlowStorageValue: StateFlowMutableKrate<Theme>
}
