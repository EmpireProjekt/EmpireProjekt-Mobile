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
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter

@Composable
fun VotesScreenComponent(
    onPop: () -> Unit,
    onClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primaryVariant)
            .padding(horizontal = AppTheme.dimens.XS),
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
            )
            Text(
                text = "На этой странице вы можете помочь серверу привлечь новых игроков проголосовав за него на других мониторингах\n" +
                    "Эта страница сделана таким образом, чтобы голосовать можно было максимально быстро и легко\n" +
                    "Просто нажмите на один вариант, а лучше на все.\n" +
                    "В появившемся окне проголосуйте за сервер",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSecondary
            )
            Spacer(Modifier.height(AppTheme.dimens.XS))
            OptionSection(modifier = Modifier) {
                Column {
                    OptionHref(
                        text = "Главный сервер",
                        onClick = {
                            onClick.invoke("https://minecraftrating.ru/vote/290898/")
                        },
                        endText = "1.21",
                        icon = MR.images.ainteractivelogo.asPainter(),
                        iconTint = Color.Unspecified,
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.dimens.XS,
                            vertical = AppTheme.dimens.XS
                        ),
                    )
                    OptionSeparator(Modifier.fillMaxWidth())
                    OptionHref(
                        text = "Главный сервер",
                        endText = "1.21.4",
                        onClick = {
                            onClick.invoke("https://minecraftrating.ru/vote/293843/")
                        },
                        icon = MR.images.ainteractivelogo.asPainter(),
                        iconTint = Color.Unspecified,
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.dimens.XS,
                            vertical = AppTheme.dimens.XS
                        ),
                    )
                    OptionSeparator(Modifier.fillMaxWidth())
                    OptionHref(
                        text = "Дополнительный 1.21",
                        onClick = {
                            onClick.invoke("https://minecraftrating.ru/vote/290896/")
                        },
                        endText = "1.21",
                        icon = MR.images.img_head_error.asPainter(),
                        iconTint = Color.Unspecified,
                        contentPadding = PaddingValues(
                            horizontal = AppTheme.dimens.XS,
                            vertical = AppTheme.dimens.XS
                        ),
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
    AdaptThemeFade {
        VotesScreenComponent(
            onPop = {},
            onClick = {}
        )
    }
}
