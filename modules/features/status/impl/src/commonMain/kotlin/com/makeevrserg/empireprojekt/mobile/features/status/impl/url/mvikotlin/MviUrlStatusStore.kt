package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.mvikotlin

import com.arkivanov.mvikotlin.core.store.Store
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Intent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.Label
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore.State

internal class MviUrlStatusStore(
    store: Store<Intent, State, Label>
) : UrlStatusStore,
    Store<Intent, State, Label> by store
