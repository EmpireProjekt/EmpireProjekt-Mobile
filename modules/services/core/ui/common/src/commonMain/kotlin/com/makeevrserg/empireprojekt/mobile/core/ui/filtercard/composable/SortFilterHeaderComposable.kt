package com.makeevrserg.empireprojekt.mobile.core.ui.filtercard.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.resources.MR
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_multiple_sort_desc
import com.makeevrserg.empireprojekt.mobile.core.resources.common_filter_title
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString

@Composable
fun SortFilterHeaderComposable() {
    TitleOptionComposable(text = MR.strings.common_filter_title.asComposableString())
    TextOptionComposable(text = MR.strings.common_filter_multiple_sort_desc.asComposableString())
}

@Preview
@Composable
private fun SortFilterHeaderPreview() {
    AdaptThemeFadeComposable {
        FilterCardComposable {
            SortFilterHeaderComposable()
        }
    }
}
