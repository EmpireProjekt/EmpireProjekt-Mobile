package com.makeevrserg.empireprojekt.mobile.features.status.impl.root.component

import com.makeevrserg.empireprojekt.mobile.features.status.api.root.component.RootStatusComponent
import com.makeevrserg.empireprojekt.mobile.features.status.api.url.component.UrlStatusComponent

internal class DefaultRootStatusComponent(
    override val statusComponents: List<UrlStatusComponent>
) : RootStatusComponent
