package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusRepository
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Action
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Message
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.State

internal fun createUrlStatusStore(
    storeFactory: StoreFactory,
    urlStatusRepository: UrlStatusRepository
): UrlStatusStore {
    val store = storeFactory.create<Intent, Action, Message, State, Label>(
        name = "UrlStatusStore",
        initialState = State.LOADING,
        bootstrapper = UrlStatusBootstrapper(),
        executorFactory = { UrlStatusExecutor(urlStatusRepository) },
        reducer = UrlStatusReducer
    )
    return MviUrlStatusStore(store)
}
