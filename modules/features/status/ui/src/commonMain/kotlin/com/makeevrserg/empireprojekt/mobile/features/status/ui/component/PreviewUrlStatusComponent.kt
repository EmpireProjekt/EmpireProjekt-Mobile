package com.makeevrserg.empireprojekt.mobile.features.status.ui.component

import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewUrlStatusComponent(
    title: String,
    status: UrlStatusComponent.LoadingStatus
) : UrlStatusComponent {
    override val model: StateFlow<UrlStatusComponent.Model> = MutableStateFlow(
        UrlStatusComponent.Model(
            title = StringDesc.Raw(title),
            isLoading = status == UrlStatusComponent.LoadingStatus.LOADING,
            status = status
        )
    )

    override fun checkStatus() = Unit
}
