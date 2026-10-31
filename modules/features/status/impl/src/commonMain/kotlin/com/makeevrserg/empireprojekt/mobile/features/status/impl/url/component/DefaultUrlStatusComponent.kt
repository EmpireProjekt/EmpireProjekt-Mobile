package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.component

import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.impl.url.api.UrlStatusStore
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.core.util.mapStateFlow

internal class DefaultUrlStatusComponent(
    title: String,
    private val urlStatusStore: UrlStatusStore
) : UrlStatusComponent {

    override val model: StateFlow<UrlStatusComponent.Model> = urlStatusStore.stateFlow.mapStateFlow { state ->
        val status = when (state) {
            UrlStatusStore.State.LOADING -> UrlStatusComponent.LoadingStatus.LOADING
            UrlStatusStore.State.SUCCESS -> UrlStatusComponent.LoadingStatus.SUCCESS
            UrlStatusStore.State.ERROR -> UrlStatusComponent.LoadingStatus.ERROR
        }
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = status == UrlStatusComponent.LoadingStatus.LOADING,
            status = status
        )
    }

    override fun checkStatus() {
        UrlStatusStore.Intent.CheckOnce(true).run(urlStatusStore::accept)
    }
}
