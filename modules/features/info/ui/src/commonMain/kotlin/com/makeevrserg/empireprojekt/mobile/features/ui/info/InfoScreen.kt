package com.makeevrserg.empireprojekt.mobile.features.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.buildkonfig.BuildKonfig
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.common.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionHref
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionInfo
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSection
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSeparator
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSwitch
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.info.ui.IR
import com.makeevrserg.empireprojekt.mobile.features.ui.info.components.LinkWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.info.data.InfoScreenLinks
import com.makeevrserg.empireprojekt.mobile.services.core.LinkBrowser

@Suppress("LongMethod")
@Composable
fun InfoScreen(
    linkBrowser: LinkBrowser,
    onTownsClick: () -> Unit,
    onRatingsClick: () -> Unit,
    onVotesClick: () -> Unit,
    onThemeToggle: () -> Unit,
    onWikiClick: () -> Unit
) {
    val models = remember { InfoScreenLinks.get() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        AstraCenterAlignedTopAppBar(title = "МЕНЮ")
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS, Alignment.Top)
        ) {
            item {
                OptionSection(modifier = Modifier) {
                    Column {
//                        OptionHref(
//                            text = "Towns",
//                            onClick = {
//                                onTownsClick.invoke()
//                            },
//                            icon = MR.images.ic_town.asPainter(),
//                            contentPadding = PaddingValues(
//                                horizontal = AppTheme.dimens.XS,
//                                vertical = AppTheme.dimens.XS
//                            ),
//                        )
                        OptionSeparator(Modifier.fillMaxWidth())
                        OptionHref(
                            text = "Ratings",
                            onClick = {
                                onRatingsClick.invoke()
                            },
                            icon = MR.images.ic_people.asPainter(),
                            contentPadding = PaddingValues(
                                horizontal = AppTheme.dimens.XS,
                                vertical = AppTheme.dimens.XS
                            ),
                        )
                        OptionSeparator(Modifier.fillMaxWidth())
                        OptionHref(
                            text = "Votes",
                            onClick = {
                                onVotesClick.invoke()
                            },
                            icon = MR.images.ic_vote.asPainter(),
                            contentPadding = PaddingValues(
                                horizontal = AppTheme.dimens.XS,
                                vertical = AppTheme.dimens.XS
                            ),
                        )
                        OptionSeparator(Modifier.fillMaxWidth())
                        OptionHref(
                            text = "WIKI",
                            onClick = {
                                onWikiClick.invoke()
                            },
                            icon = MR.images.ic_knowledge.asPainter(),
                            contentPadding = PaddingValues(
                                horizontal = AppTheme.dimens.XS,
                                vertical = AppTheme.dimens.XS
                            ),
                        )
                        OptionSeparator(Modifier.fillMaxWidth())
                        OptionSwitch(
                            text = "Темная тема",
                            onCheckChange = {
                                onThemeToggle.invoke()
                            },
                            checked = AppTheme.astraColors.isDark,
                            icon = MR.images.ic_theme.asPainter(),
                            contentPadding = PaddingValues(
                                horizontal = AppTheme.dimens.XS,
                                vertical = AppTheme.dimens.XS
                            ),
                        )
                    }
                }
            }

            item {
                Column {
                    Text(
                        text = IR.strings.info_more_links.asComposableString(),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary,
                        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
                    )
                    Text(
                        text = "All this links associated with EmpireProjekt and AstraInteractive. " +
                            "This text specially displayed for google play " +
                            "support which considering this links as advertisement.",
                        style = MaterialTheme.typography.overline,
                        color = MaterialTheme.colors.onPrimary,
                        fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
                    )
                }
            }

            item {
                OptionSection(modifier = Modifier) {
                    Column {
                        models.forEachIndexed { i, linkModel ->
                            LinkWidget(
                                linkBrowser = linkBrowser,
                                linkModel = linkModel
                            )
                            if (i != models.lastIndex) {
                                OptionSeparator(Modifier.fillMaxWidth())
                            }
                        }
                    }
                }
            }
            item {
                OptionSection(modifier = Modifier) {
                    OptionInfo(
                        modifier = Modifier.padding(vertical = AppTheme.dimens.XS),
                        icon = rememberVectorPainter(Icons.Filled.Bolt),
                        text = "Version",
                        endText = "${BuildKonfig.VERSION_CODE} (${BuildKonfig.VERSION_NAME})",
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.dimens.XS,
                            vertical = AppTheme.dimens.XS
                        ),
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.navBarsPadding())
            }
            item {
                Spacer(modifier = Modifier.height(AppTheme.dimens.L))
            }
        }
    }
}

@Composable
@Preview
private fun InfoScreenPreview() {
    AdaptThemeFade {
        InfoScreen(
            linkBrowser = object : LinkBrowser {
                override fun openInBrowser(url: String) = Unit
            },
            onRatingsClick = {},
            onTownsClick = {},
            onVotesClick = {},
            onThemeToggle = {},
            onWikiClick = {}
        )
    }
}
