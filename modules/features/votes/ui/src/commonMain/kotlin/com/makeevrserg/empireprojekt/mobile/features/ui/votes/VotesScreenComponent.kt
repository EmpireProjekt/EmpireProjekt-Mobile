package com.makeevrserg.empireprojekt.mobile.features.ui.votes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.common.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionHref
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSection
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSeparator
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asFontFamily
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.features.ui.votes.model.VoteUrl

@Suppress("LongMethod", "MaximumLineLength", "MaxLineLength")
@Composable
fun VotesScreenComponent(
    onPop: () -> Unit,
    onClick: (String) -> Unit
) {
    val voteUrls = listOf(
        VoteUrl(
            name = "FrontierSmp",
            url = "https://minecraftrating.ru/vote/290898/",
            hasPrize = false,
            image = MR.images.img_minecraftrating
        ),
        VoteUrl(
            name = "WildernessSmp",
            url = "https://minecraftrating.ru/vote/295288/",
            hasPrize = false,
            image = MR.images.img_minecraftrating
        ),
        VoteUrl(
            name = "EndureCraft",
            url = "https://minecraftrating.ru/vote/295287/",
            hasPrize = false,
            image = MR.images.img_minecraftrating
        ),
        VoteUrl(
            name = "ExploreSmp",
            url = "https://minecraftrating.ru/vote/293843/",
            hasPrize = false,
            image = MR.images.img_minecraftrating
        ),
        VoteUrl(
            name = "LongExile",
            url = "https://minecraftrating.ru/vote/290896/",
            hasPrize = false,
            image = MR.images.img_minecraftrating
        ),
        VoteUrl(
            url = "https://minecraftrating.ru/projects/empireprojekt/",
            hasPrize = true,
            image = MR.images.img_minecraftrating,
            name = "Server Project"
        ),
        VoteUrl(
            name = "FrontierSurvival",
            url = "https://hotmc.ru/minecraft-server-200387",
            hasPrize = true,
            image = MR.images.img_hotmc
        ),
        VoteUrl(
            name = "WildernessSmp",
            url = "https://hotmc.ru/minecraft-server-258775",
            hasPrize = true,
            image = MR.images.img_hotmc
        ),
        VoteUrl(
            name = "EndureCraft",
            url = "https://hotmc.ru/minecraft-server-258776",
            hasPrize = true,
            image = MR.images.img_hotmc
        ),
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = AppTheme.dimens.S),
        topBar = {
            AstraCenterAlignedTopAppBar(
                title = "ГОЛОСОВАНИЕ",
                onBackClicked = onPop
            )
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            Text(
                text = "Помогите серверу собственноручно!".uppercase(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily()
            )
            Text(
                text = "На этой странице вы можете помочь серверу привлечь новых игроков проголосовав за него на других мониторингах\n" +
                    "Просто нажмите на один вариант, а лучше на все.\n",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily(),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(AppTheme.dimens.XS))
            voteUrls.groupBy { it.hasPrize }
                .forEach { (hasPrize, voteUrls) ->
                    if (hasPrize) {
                        Spacer(Modifier.height(AppTheme.dimens.XS))
                        Text(
                            text = "При голосовании за эти сервера вы получите награду!",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSecondary,
                            fontFamily = MR.fonts.jetbrainsmono_wght.asFontFamily(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(AppTheme.dimens.XS))
                    }
                    OptionSection(modifier = Modifier) {
                        Column {
                            voteUrls.forEachIndexed { index, voteUrl ->
                                OptionHref(
                                    text = voteUrl.name,
                                    onClick = {
                                        onClick.invoke(voteUrl.url)
                                    },
                                    icon = voteUrl.image.asPainter(),
                                    iconTint = Color.Unspecified,
                                    contentPadding = PaddingValues(
                                        horizontal = AppTheme.dimens.XS,
                                        vertical = AppTheme.dimens.XS
                                    ),
                                )
                                if (index != voteUrls.lastIndex) {
                                    OptionSeparator(Modifier.fillMaxWidth())
                                }
                            }
                        }
                    }
                }
            Spacer(Modifier.navBarsPadding())
        }
    }
}

@Composable
@Preview
private fun VotesScreenComponentPreview() {
    AdaptThemeFade {
        VotesScreenComponent(
            onPop = {},
            onClick = {}
        )
    }
}
