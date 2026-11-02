package com.makeevrserg.empireprojekt.mobile.features.status.ui.component

import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.model.UrlStatusState
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class PreviewUrlStatusComponent(
    title: String,
    status: UrlStatusState.LoadingStatus
) : UrlStatusComponent {
    override val model: StateFlow<UrlStatusState> = MutableStateFlow(
        UrlStatusState(
            title = StringDesc.Raw(title),
            isLoading = status == UrlStatusState.LoadingStatus.LOADING,
            status = status
        )
    )

    override fun checkStatus() = Unit
}
