package com.makeevrserg.empireprojekt.mobile.features.ui.rating.userlist.composable.component

import androidx.compose.animation.Crossfade
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.composable.AstraCenterAlignedTopAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.button.composable.AstraIconButtonComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.composable.AdaptThemeFadeComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.composable.SearchAppBarComposable
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.model.SearchBarState
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_users_title

@Composable
fun RatingUsersAppBar(
    query: String,
    searchBarState: SearchBarState,
    onSearchBarStateChange: (SearchBarState) -> Unit,
    onUpdateQuery: (String) -> Unit,
    onBack: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    Crossfade(modifier = modifier, targetState = searchBarState) { state ->
        if (state == SearchBarState.Open) {
            SearchAppBarComposable(
                query = query,
                onTextChange = onUpdateQuery,
                onCloseClick = {
                    onUpdateQuery.invoke("")
                    onSearchBarStateChange.invoke(SearchBarState.Closed)
                }
            )
        } else {
            AstraCenterAlignedTopAppBarComposable(
                title = RR.strings.rating_users_title.asComposableString(),
                onBackClick = onBack,
                actions = {
                    AstraIconButtonComposable(
                        imageVector = Icons.Filled.Search,
                        onClick = { onSearchBarStateChange.invoke(SearchBarState.Open) },
                        modifier = Modifier.alpha(ContentAlpha.medium)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun RatingUsersAppBarClosedPreview() {
    AdaptThemeFadeComposable {
        RatingUsersAppBar(
            query = "",
            searchBarState = SearchBarState.Closed,
            onSearchBarStateChange = {},
            onUpdateQuery = {},
            onBack = {}
        )
    }
}

@Preview
@Composable
private fun RatingUsersAppBarSearchEmptyPreview() {
    AdaptThemeFadeComposable {
        RatingUsersAppBar(
            query = "",
            searchBarState = SearchBarState.Open,
            onSearchBarStateChange = {},
            onUpdateQuery = {},
            onBack = {}
        )
    }
}

@Preview
@Composable
private fun RatingUsersAppBarSearchPreview() {
    AdaptThemeFadeComposable {
        RatingUsersAppBar(
            query = "RomaRoman",
            searchBarState = SearchBarState.Open,
            onSearchBarStateChange = {},
            onUpdateQuery = {},
            onBack = {}
        )
    }
}
