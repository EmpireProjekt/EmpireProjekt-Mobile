package com.makeevrserg.empireprojekt.mobile.features.ui.info.composable.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_github
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionHrefComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.theme.OptionSpacing
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.ui.info.model.Link

@Composable
internal fun LinkWidgetComposable(
    linkModel: Link,
    onClick: () -> Unit
) {
    OptionHrefComposable(
        icon = linkModel.res.asPainter(),
        text = linkModel.title,
        iconTint = linkModel.tint.invoke(),
        contentPadding = OptionSpacing.ContentPadding,
        onClick = onClick,
    )
}

@Preview
@Composable
private fun LinkWidgetPreview() {
    AdaptThemeFadeComposable {
        LinkWidgetComposable(
            linkModel = Link(
                res = MR.images.ic_github,
                title = "Astra Github",
                url = "https://github.com/Astra-Interactive/"
            ),
            onClick = {}
        )
    }
}
