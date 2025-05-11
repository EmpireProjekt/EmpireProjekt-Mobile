package com.makeevrserg.empireprojekt.mobile.features.ui.info.components

import androidx.compose.runtime.Composable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionHref
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
//        tint = linkModel.tint.invoke(), // todo
        onClick = {
            linkBrowser.openInBrowser(linkModel.url)
        }
    )
}
