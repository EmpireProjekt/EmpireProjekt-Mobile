package com.makeevrserg.empireprojekt.mobile.features.info.ui.util

import androidx.compose.material.MaterialTheme
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_ainteractivelogo
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_alearner
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_boosty
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_esmptelegram
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_github
import com.makeevrserg.empireprojekt.mobile.core.resources.img_gplay
import com.makeevrserg.empireprojekt.mobile.core.resources.img_splash
import com.makeevrserg.empireprojekt.mobile.features.info.ui.model.Link

internal fun buildInfoScreenLinks(): List<Link> {
    return buildList {
        Link(
            title = "Astra Github",
            res = MR.images.ic_github,
            tint = { MaterialTheme.colors.onPrimary },
            url = "https://github.com/Astra-Interactive/"
        ).run(::add)
        Link(
            title = "Author Github",
            res = MR.images.ic_github,
            tint = { MaterialTheme.colors.onPrimary },
            url = "https://github.com/makeevrserg"
        ).run(::add)
        Link(
            title = "Source code",
            res = MR.images.ic_github,
            tint = { MaterialTheme.colors.onPrimary },
            url = "https://github.com/makeevrserg/EmpireProjekt-Mobile"
        ).run(::add)
        Link(
            title = "TG Group",
            res = MR.images.ic_esmptelegram,
            url = "https://t.me/empiresmp"
        ).run(::add)
        Link(
            title = "TG Chat",
            res = MR.images.ic_esmptelegram,
            url = "https://t.me/empiresmp_discussion"
        ).run(::add)
        Link(
            title = "EmpireProjekt.ru",
            res = MR.images.img_splash,
            url = "https://EmpireProjekt.ru"
        ).run(::add)
        Link(
            title = "AstraInteractive.ru",
            res = MR.images.ic_ainteractivelogo,
            url = "https://AstraInteractive.ru"
        ).run(::add)
        Link(
            title = "Boosty",
            res = MR.images.ic_boosty,
            url = "https://boosty.to/empireprojekt/donate"
        ).run(::add)
        Link(
            title = "AstraLearner",
            res = MR.images.ic_alearner,
            url = "https://play.google.com/store/apps/details?id=com.makeevrserg.astralearner"
        ).run(::add)
        Link(
            title = "Google Play page",
            res = MR.images.img_gplay,
            url = "https://play.google.com/store/apps/details?id=com.makeevrserg.empireprojekt.mobile"
        ).run(::add)
    }
}
