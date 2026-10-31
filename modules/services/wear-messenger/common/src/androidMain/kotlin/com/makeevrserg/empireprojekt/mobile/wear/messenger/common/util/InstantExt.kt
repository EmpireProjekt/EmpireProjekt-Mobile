package com.makeevrserg.empireprojekt.mobile.wear.messenger.common.util

import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.time.Instant

private const val CLOCK_TIME_PATTERN = "HH:mm:ss"
private const val EMPTY_CLOCK_PLACEHOLDER = "..."

internal fun Instant?.formatClockTime(): String {
    if (this == null) return EMPTY_CLOCK_PLACEHOLDER
    return SimpleDateFormat(CLOCK_TIME_PATTERN, Locale.getDefault()).format(toEpochMilliseconds())
}
