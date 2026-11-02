package com.makeevrserg.empireprojekt.mobile.features.towns.ui.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.common.player.composable.PlayerNameRowComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.text.composable.AstraTextComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionInfoComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.feature.towns.TR
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_created_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_entrance_closed_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_entrance_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_entrance_open_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_founder_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_nation_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_outlaws_count_label
import com.makeevrserg.empireprojekt.mobile.feature.towns.towns_details_tag_label
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter
import kotlin.time.Instant

@Suppress("LongMethod")
@Composable
internal fun TownCardComposable(
    mayor: String,
    townName: String,
    board: String,
    founder: String,
    nation: String,
    outlawsAmount: Int,
    tag: String,
    registered: Long,
    residentsCount: Long,
    isOpen: Boolean
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Column(
        modifier = Modifier
            .astraCard(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(AppTheme.dimens.XS)
            )
            .padding(
                vertical = AppTheme.dimens.XS,
                horizontal = AppTheme.dimens.S
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            PlayerNameRowComposable(uuid = mayor, name = mayor)
            AstraTextComposable(
                text = residentsCount.toString(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimens.XS))
                    .background(AppTheme.astraColors.astraLogo.astraYellow)
                    .padding(horizontal = AppTheme.dimens.XS)
                    .padding(vertical = AppTheme.dimens.XXS)
            )
        }
        AstraTextComposable(
            text = townName,
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Center
        )
        if (board.isNotBlank()) {
            AstraTextComposable(
                text = board,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary
            )
        }
        OptionInfoComposable(
            text = TR.strings.towns_details_tag_label.asComposableString(),
            endText = tag,
            modifier = Modifier.fillMaxWidth()
        )
        OptionInfoComposable(
            text = TR.strings.towns_details_founder_label.asComposableString(),
            endText = founder,
            modifier = Modifier.fillMaxWidth()
        )
        if (nation.isNotBlank()) {
            OptionInfoComposable(
                text = TR.strings.towns_details_nation_label.asComposableString(),
                endText = nation,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (outlawsAmount > 0) {
            OptionInfoComposable(
                text = TR.strings.towns_details_outlaws_count_label.asComposableString(),
                endText = outlawsAmount.toString(),
                modifier = Modifier.fillMaxWidth()
            )
        }

        OptionInfoComposable(
            text = TR.strings.towns_details_created_label.asComposableString(),
            endText = remember {
                timeFormatter.format(
                    instant = Instant.fromEpochMilliseconds(registered),
                    format = "dd.MM.yyyy"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        OptionInfoComposable(
            text = TR.strings.towns_details_entrance_label.asComposableString(),
            endText = when (isOpen) {
                true -> TR.strings.towns_details_entrance_open_label.asComposableString()
                false -> TR.strings.towns_details_entrance_closed_label.asComposableString()
            },
            iconTint = when (isOpen) {
                true -> AppTheme.astraColors.action.colorPositive
                false -> AppTheme.astraColors.action.colorNegative
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun TownCardOpenPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownCardComposable(
                mayor = "RomaRoman",
                townName = "Rostov",
                board = "Международный контролирующий орган по борьбе с пивом",
                founder = "cinnamonrein",
                nation = "NCR",
                outlawsAmount = 1,
                tag = "NCR",
                registered = 1706549308031,
                residentsCount = 10,
                isOpen = true
            )
        }
    }
}

@Preview
@Composable
private fun TownCardClosedPreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        Box(modifier = Modifier.background(MaterialTheme.colors.primaryVariant)) {
            TownCardComposable(
                mayor = "cinnamonrein",
                townName = "Moscow",
                board = "",
                founder = "cinnamonrein",
                nation = "",
                outlawsAmount = 0,
                tag = "MSK",
                registered = 1706549308031,
                residentsCount = 1,
                isOpen = false
            )
        }
    }
}
