package com.makeevrserg.empireprojekt.mobile.features.ui.rating.users.components

import androidx.compose.animation.Crossfade
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import com.makeevrserg.empireprojekt.mobile.core.ui.appbar.AstraCenterAlignedTopAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchAppBar
import com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.SearchBarState
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade
import com.makeevrserg.empireprojekt.mobile.core.ui.util.asComposableString
import com.makeevrserg.empireprojekt.mobile.rating.RR
import com.makeevrserg.empireprojekt.mobile.rating.rating_users_title

@Composable
fun RatingUsersAppBar(
    query: String,
    onUpdateQuery: (String) -> Unit,
    onBack: (() -> Unit)?,
    modifier: Modifier = Modifier
) {
    var searchBarState by remember {
        val state = if (query.isEmpty()) SearchBarState.Closed else SearchBarState.Open
        mutableStateOf(state)
    }
    Crossfade(modifier = modifier, targetState = searchBarState) { state ->
        if (state == SearchBarState.Open) {
            SearchAppBar(
                query = query,
                onTextChange = onUpdateQuery,
                onCloseClick = {
                    onUpdateQuery.invoke("")
                    searchBarState = SearchBarState.Closed
                }
            )
        } else {
            AstraCenterAlignedTopAppBar(
                title = RR.strings.rating_users_title.asComposableString(),
                onBackClick = onBack,
                actions = {
                    IconButton(
                        onClick = { searchBarState = SearchBarState.Open },
                        modifier = Modifier.alpha(ContentAlpha.medium)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
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
            onUpdateQuery = {},
            onBack = {}
        )
    }
}
