package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.data.paging

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.api.model.TownsFilter
import ru.astrainteractive.klibs.paging.context.PageContext

internal data class TownyPageContext(
    val page: Int = 0,
    val filter: TownsFilter
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
