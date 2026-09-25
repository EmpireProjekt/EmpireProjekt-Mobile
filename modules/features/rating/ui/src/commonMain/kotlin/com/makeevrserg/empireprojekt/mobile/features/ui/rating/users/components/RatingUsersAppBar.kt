package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.animation.Crossfade
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.button.AstraIconButton
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchBarState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
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
            SearchAppBar(
                query = query,
                onTextChange = onUpdateQuery,
                onCloseClick = {
                    onUpdateQuery.invoke("")
                    onSearchBarStateChange.invoke(SearchBarState.Closed)
                }
            )
        } else {
            AstraCenterAlignedTopAppBar(
                title = RR.strings.rating_users_title.asComposableString(),
                onBackClick = onBack,
                actions = {
                    AstraIconButton(
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
    AdaptThemeFade {
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
    AdaptThemeFade {
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
    AdaptThemeFade {
        RatingUsersAppBar(
            query = "RomaRoman",
            searchBarState = SearchBarState.Open,
            onSearchBarStateChange = {},
            onUpdateQuery = {},
            onBack = {}
        )
    }
}
