package com.makeevrserg.empireprojekt.mobile.features.ui.info.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_github
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionDefaults
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionHref
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.ui.info.model.LinkModel
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

@Composable
internal fun LinkWidget(
    linkBrowser: LinkBrowser,
    linkModel: LinkModel
) {
    OptionHref(
        icon = linkModel.res.asPainter(),
        text = linkModel.title,
        iconTint = linkModel.tint.invoke(),
        contentPadding = OptionDefaults.ContentPadding,
        onClick = {
            linkBrowser.openInBrowser(linkModel.url)
        },
    )
}

@Preview
@Composable
private fun LinkWidgetPreview() {
    AdaptThemeFade {
        LinkWidget(
            linkBrowser = object : LinkBrowser {
                override fun openInBrowser(url: String) = Unit
            },
            linkModel = LinkModel(
                res = MR.images.ic_github,
                title = "Astra Github",
                url = "https://github.com/Astra-Interactive/"
            )
        )
    }
}
