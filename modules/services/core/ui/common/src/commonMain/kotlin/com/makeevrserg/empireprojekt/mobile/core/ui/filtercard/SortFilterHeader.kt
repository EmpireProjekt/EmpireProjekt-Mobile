package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_multiple_sort_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_title
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString

/**
 * Title and hint opening a [FilterCard] whose options sort by several fields at once.
 */
@Composable
fun FilterCardScope.SortFilterHeader() {
    TitleOption(text = MR.strings.common_filter_title.asComposableString())
    TextOption(text = MR.strings.common_filter_multiple_sort_desc.asComposableString())
}

@Preview
@Composable
private fun SortFilterHeaderPreview() {
    AdaptThemeFade {
        FilterCard {
            SortFilterHeader()
        }
    }
}
