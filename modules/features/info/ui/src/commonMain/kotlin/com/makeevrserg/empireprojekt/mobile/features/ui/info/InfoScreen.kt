package com.makeevrserg.empireprojekt.mobile.features.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
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
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_knowledge
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_people
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_theme
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_vote
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable.AstraTopBarScreenComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionHrefComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionInfoComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionSeparatorComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionSwitchComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.layout.OptionSectionComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.model.OptionSectionContent
import com.makeevrserg.empireprojekt.mobile.core.ui.option.theme.OptionSpacing
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.util.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.features.info.ui.IR
import com.makeevrserg.empireprojekt.mobile.features.info.ui.info_links_title
import com.makeevrserg.empireprojekt.mobile.features.ui.info.composable.component.LinkWidget
import com.makeevrserg.empireprojekt.mobile.features.ui.info.data.InfoScreenLinks

@Suppress("LongMethod", "UnusedParameter")
@Composable
fun InfoScreen(
    onLinkClick: (String) -> Unit,
    onTownsClick: () -> Unit,
    onRatingsClick: () -> Unit,
    onVotesClick: () -> Unit,
    onThemeToggle: () -> Unit,
    onWikiClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val models = remember { InfoScreenLinks.get() }
    AstraTopBarScreenComposable(title = "МЕНЮ", modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = AppTheme.dimens.XS),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS, Alignment.Top)
        ) {
            item {
                OptionSectionComposable(modifier = Modifier) {
                    Column {
//                        OptionHrefComposable(
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
                        OptionSeparatorComposable(Modifier.fillMaxWidth())
                        OptionHrefComposable(
                            text = "Ratings",
                            onClick = {
                                onRatingsClick.invoke()
                            },
                            icon = MR.images.ic_people.asPainter(),
                            contentPadding = OptionSpacing.ContentPadding,
                        )
                        OptionSeparatorComposable(Modifier.fillMaxWidth())
                        OptionHrefComposable(
                            text = "Votes",
                            onClick = {
                                onVotesClick.invoke()
                            },
                            icon = MR.images.ic_vote.asPainter(),
                            contentPadding = OptionSpacing.ContentPadding,
                        )
                        OptionSeparatorComposable(Modifier.fillMaxWidth())
                        OptionHrefComposable(
                            text = "WIKI",
                            onClick = {
                                onWikiClick.invoke()
                            },
                            icon = MR.images.ic_knowledge.asPainter(),
                            contentPadding = OptionSpacing.ContentPadding,
                        )
                        OptionSeparatorComposable(Modifier.fillMaxWidth())
                        OptionSwitchComposable(
                            text = "Темная тема",
                            onCheckChange = {
                                onThemeToggle.invoke()
                            },
                            checked = AppTheme.astraColors.isDark,
                            icon = MR.images.ic_theme.asPainter(),
                            contentPadding = OptionSpacing.ContentPadding,
                        )
                    }
                }
            }

            item {
                Column {
                    AstraTextComposable(
                        text = IR.strings.info_links_title.asComposableString(),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onPrimary
                    )
                    AstraTextComposable(
                        text = "All this links associated with EmpireProjekt and AstraInteractive. " +
                            "This text specially displayed for google play " +
                            "support which considering this links as advertisement.",
                        style = MaterialTheme.typography.overline,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }

            item {
                OptionSectionComposable(section = OptionSectionContent(models)) { linkModel ->
                    LinkWidget(
                        linkModel = linkModel,
                        onClick = { onLinkClick.invoke(linkModel.url) }
                    )
                }
            }
            item {
                OptionSectionComposable(modifier = Modifier) {
                    OptionInfoComposable(
                        modifier = Modifier.padding(vertical = AppTheme.dimens.XS),
                        icon = rememberVectorPainter(Icons.Filled.Bolt),
                        text = "Version",
                        endText = "${BuildKonfig.VERSION_CODE} (${BuildKonfig.VERSION_NAME})",
                        contentPadding = OptionSpacing.ContentPadding,
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
private fun InfoScreenDarkPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        InfoScreen(
            onLinkClick = {},
            onRatingsClick = {},
            onTownsClick = {},
            onVotesClick = {},
            onThemeToggle = {},
            onWikiClick = {}
        )
    }
}

@Composable
@Preview
private fun InfoScreenLightPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.LIGHT) {
        InfoScreen(
            onLinkClick = {},
            onRatingsClick = {},
            onTownsClick = {},
            onVotesClick = {},
            onThemeToggle = {},
            onWikiClick = {}
        )
    }
}
