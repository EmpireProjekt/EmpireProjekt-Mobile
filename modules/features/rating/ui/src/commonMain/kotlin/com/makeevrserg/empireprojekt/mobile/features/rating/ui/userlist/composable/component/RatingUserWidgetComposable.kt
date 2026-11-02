package com.makeevrserg.empireprojekt.mobile.features.rating.ui.userlist.composable.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_calendar_today
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_raised_hand
import com.makeevrserg.empireprojekt.mobile.core.resources.ic_thumb_up_down
import com.makeevrserg.empireprojekt.mobile.core.ui.common.player.composable.PlayerNameRowComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.asPainter
import com.makeevrserg.empireprojekt.mobile.core.ui.common.util.astraCard
import com.makeevrserg.empireprojekt.mobile.core.ui.option.composable.component.OptionInfoComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.model.ComposeTheme
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.theme.AppTheme
import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUser
import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_last_updated_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_users_total_rating_label
import com.makeevrserg.empireprojekt.mobile.rating.rating_users_votes_count_label
import ru.astrainteractive.klibs.mikro.extensions.JvmTimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.TimeFormatter
import kotlin.time.Instant

@Suppress("LongMethod", "MagicNumber")
@Composable
internal fun RatingUserWidgetComposable(
    model: RatingUser,
    onClick: () -> Unit
) {
    val timeFormatter: TimeFormatter = JvmTimeFormatter()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .astraCard(color = MaterialTheme.colors.primary)
            .clickable { onClick.invoke() },
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    vertical = AppTheme.dimens.XS,
                    horizontal = AppTheme.dimens.S
                ),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.XS)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PlayerNameRowComposable(uuid = model.minecraftUUID, name = model.minecraftName)
                        Spacer(Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary,
                            modifier = Modifier.size(AppTheme.dimens.M)
                        )
                    }
                    OptionInfoComposable(
                        text = RR.strings.rating_users_total_rating_label.asComposableString(),
                        endText = "${model.totalRating}",
                        modifier = Modifier.fillMaxWidth(),
                        icon = MR.images.ic_thumb_up_down.asPainter(),
                    )
                    OptionInfoComposable(
                        text = RR.strings.rating_users_votes_count_label.asComposableString(),
                        endText = "${model.ratingVotes}",
                        modifier = Modifier.fillMaxWidth(),
                        icon = MR.images.ic_raised_hand.asPainter(),
                    )
                    OptionInfoComposable(
                        icon = MR.images.ic_calendar_today.asPainter(),
                        text = RR.strings.rating_last_updated_label.asComposableString(),
                        endText = remember {
                            timeFormatter.format(
                                instant = Instant.fromEpochMilliseconds(model.lastUpdated),
                                format = "dd.MM.yyyy"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            GradientProgressIndicatorComposable(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFb00000),
                        Color(0xFFb03800),
                        Color(0xFFb0aa00),
                        Color(0xFF81b000),
                        Color(0xFF2fb000),
                        Color(0xFF00b03e),
                        Color(0xFF00b0a7),
                        Color(0xFF0093b0)
                    )
                ),
                progress = let {
                    val coerse = 10L
                    val total = model.totalRating.plus(coerse).coerceIn(minimumValue = 0, maximumValue = coerse * 2)
                    total.div(coerse * 2f).coerceIn(minimumValue = 0f, maximumValue = 1f)
                }
            )
        }
    }
}

@Composable
@Preview
private fun RatingUserWidgetPositivePreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserWidgetComposable(
            model = RatingUser(
                id = 1,
                minecraftUUID = "uuid",
                minecraftName = "name",
                lastUpdated = 0,
                totalRating = 10,
                ratingVotes = 1
            ),
            onClick = {}
        )
    }
}

@Composable
@Preview
private fun RatingUserWidgetNegativePreview() {
    AdaptThemeFadeComposable(composeTheme = ComposeTheme.DARK) {
        RatingUserWidgetComposable(
            model = RatingUser(
                id = 2,
                minecraftUUID = "uuid",
                minecraftName = "name",
                lastUpdated = 0,
                totalRating = -10,
                ratingVotes = 12
            ),
            onClick = {}
        )
    }
}
