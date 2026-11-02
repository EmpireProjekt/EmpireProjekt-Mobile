package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.model

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import ru.astrainteractive.klibs.paging.context.PageContext

internal data class TownyPageContext(
    val page: Int,
    val filter: TownsFilter
) : PageContext
