package com.makeevrserg.empireprojekt.mobile.features.ui.rating.user.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_calendar_today
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_history_edu
import com.makeevrserg.empireprojekt.mobile.core.ui.common.PlayerNameRow
import com.makeevrserg.empireprojekt.mobile.core.ui.common.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionInfo
import com.makeevrserg.empireprojekt.mobile.core.ui.option.OptionSeparator
import com.makeevrserg.empireprojekt.mobile.core.ui.text.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asPainter
import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_last_updated_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_user_message_label
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.Instant

@Suppress("LongMethod")
@Composable
internal fun RatingUserWidget(
    uuid: String?,
    name: String?,
    rating: Int,
    message: String,
    time: Long
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .astraCard()
            .padding(vertical = AppTheme.dimens.XS)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.S),
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            ) {
                PlayerNameRow(
                    uuid = uuid.orEmpty(),
                    name = name ?: "-",
                    style = MaterialTheme.typography.subtitle2,
                    spacing = AppTheme.dimens.S
                )
                Spacer(Modifier.weight(1f))
                when {
                    rating > 0 -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbUp,
                            contentDescription = null,
                            tint = AppTheme.astraColors.action.colorPositive,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }

                    else -> {
                        Icon(
                            imageVector = Icons.Filled.ThumbDown,
                            contentDescription = null,
                            tint = AppTheme.astraColors.action.colorNegative,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }
                }
            }
            Spacer(Modifier.height(AppTheme.dimens.XS))
            OptionSeparator(Modifier.fillMaxWidth())
            Spacer(Modifier.height(AppTheme.dimens.XS))
            OptionInfo(
                text = RR.strings.rating_last_updated_label.asComposableString(),
                endText = remember {
                    timeFormatter.format(
                        instant = Instant.fromEpochMilliseconds(time),
                        format = "dd.MM.yyyy HH:mm"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimens.S),
                icon = MR.images.ic_calendar_today.asPainter()
            )
            OptionInfo(
                text = RR.strings.rating_user_message_label.asComposableString(),
                endText = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = AppTheme.dimens.S),
                icon = MR.images.ic_history_edu.asPainter()
            )
            AstraText(
                text = message.trim(),
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(horizontal = AppTheme.dimens.S)
            )
        }
    }
}

@Preview
@Composable
private fun RatingUserWidgetPositivePreview() {
    AdaptThemeFade {
        RatingUserWidget(
            uuid = UUID.randomUUID().toString(),
            name = "RomaRoman",
            rating = 10,
            message = "Hello world",
            time = Clock.System.now().toEpochMilliseconds()
        )
    }
}

@Preview
@Composable
private fun RatingUserWidgetNegativePreview() {
    AdaptThemeFade {
        RatingUserWidget(
            uuid = null,
            name = null,
            rating = -1,
            message = "Griefed my base.",
            time = Clock.System.now().toEpochMilliseconds()
        )
    }
}
