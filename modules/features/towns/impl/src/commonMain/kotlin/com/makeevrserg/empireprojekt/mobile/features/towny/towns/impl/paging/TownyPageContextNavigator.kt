package com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.paging

import com.makeevrserg.empireprojekt.mobile.features.towny.towns.impl.model.TownyPageContext
import ru.astrainteractive.klibs.paging.context.PageContext

internal object TownyPageContextNavigator : PageContext.Factory<TownyPageContext> {
    override fun next(pageContext: TownyPageContext): TownyPageContext {
        return pageContext.copy(page = pageContext.page + 1)
    }

    override fun prev(pageContext: TownyPageContext): TownyPageContext {
        return pageContext.copy(page = pageContext.page - 1)
    }
}
