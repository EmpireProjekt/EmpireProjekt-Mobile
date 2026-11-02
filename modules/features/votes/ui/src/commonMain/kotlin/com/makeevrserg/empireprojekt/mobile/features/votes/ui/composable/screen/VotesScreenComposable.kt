package com.makeevrserg.empireprojekt.mobile.features.votes.ui.composable.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.img_hotmc
import com.makeevrserg.empireprojekt.mobile.core.resources.img_minecraftrating
import com.makeevrserg.empireprojekt.mobile.core.ui.common.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.navBarsPadding
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionHrefComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.layout.OptionSectionComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.option.model.OptionSectionContent
import com.makeevrserg.empireprojekt.mobile.core.ui.option.theme.OptionSpacing
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.votes.ui.model.VoteUrl

@Suppress("LongMethod", "MaximumLineLength", "MaxLineLength")
@Composable
fun VotesScreenComposable(
    onPop: () -> Unit,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
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
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = AppTheme.dimens.S),
        topBar = {
            AstraCenterAlignedTopAppBarComposable(
                title = "ГОЛОСОВАНИЕ",
                onBackClick = onPop
            )
        }
    ) { scaffoldPadding ->
        Column(modifier = Modifier.padding(scaffoldPadding)) {
            AstraTextComposable(
                text = "Помогите серверу собственноручно!".uppercase(),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            AstraTextComposable(
                text = "На этой странице вы можете помочь серверу " +
                    "привлечь новых игроков проголосовав за него на других мониторингах\n" +
                    "Просто нажмите на один вариант, а лучше на все.\n",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(AppTheme.dimens.XS))
            voteUrls.groupBy(VoteUrl::hasPrize)
                .forEach { (hasPrize, prizeVoteUrls) ->
                    if (hasPrize) {
                        Spacer(Modifier.height(AppTheme.dimens.XS))
                        AstraTextComposable(
                            text = "При голосовании за эти сервера вы получите награду!",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSecondary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(AppTheme.dimens.XS))
                    }
                    OptionSectionComposable(section = OptionSectionContent(prizeVoteUrls)) { voteUrl ->
                        OptionHrefComposable(
                            text = voteUrl.name,
                            onClick = {
                                onClick.invoke(voteUrl.url)
                            },
                            icon = voteUrl.image.asPainter(),
                            iconTint = Color.Unspecified,
                            contentPadding = OptionSpacing.ContentPadding,
                        )
                    }
                }
            Spacer(Modifier.navBarsPadding())
        }
    }
}

@Composable
@Preview
private fun VotesScreenComponentPreview() {
    AdaptThemeFadeComposable {
        VotesScreenComposable(
            onPop = {},
            onClick = {}
        )
    }
}
