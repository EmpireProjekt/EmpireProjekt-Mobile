package com.makeevrserg.empireprojekt.mobile.features.towny.towns.data.paging

import ru.astrainteractive.empireapi.models.towny.TownsFilterModel
import ru.astrainteractive.klibs.paging.collector.DefaultPagingCollector
import ru.astrainteractive.klibs.paging.collector.PagingCollector
import ru.astrainteractive.klibs.paging.data.PagedListDataSource
import ru.astrainteractive.klibs.paging.state.PageResult
import ru.astrainteractive.klibs.paging.state.PagingState

internal class TownyPagingCollector<T>(
    private val initialPage: Int = 0,
    private val pager: PagedListDataSource<T, TownyPageContext>,
    private val initialFilterFactory: () -> TownsFilterModel
) : PagingCollector<T, TownyPageContext> by DefaultPagingCollector(
    initialPagingStateFactory = {
        PagingState(
            pageContext = TownyPageContext(
                page = initialPage,
                filter = initialFilterFactory.invoke()
            ),
            pageResult = PageResult.Pending,
            items = emptyList()
        )
    },
    pager = pager,
    pageContextFactory = TownyPageContext.Factory
)
