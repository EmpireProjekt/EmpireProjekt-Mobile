package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging

import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.paging.context.PageContext

internal data class TownyPageContext(
    val page: Int = 0,
    val filter: TownsFilterModel
) : PageContext {
    object Factory : PageContext.Factory<TownyPageContext> {
        override fun next(pageContext: TownyPageContext): TownyPageContext {
            return pageContext.copy(page = pageContext.page + 1)
        }

        override fun prev(pageContext: TownyPageContext): TownyPageContext {
            return pageContext.copy(page = pageContext.page - 1)
        }
    }
}
