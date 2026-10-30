package com.makeevrserg.empireprojekt.mobile.core.ui.searchbar.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.makeevrserg.empireprojekt.mobile.core.ui.button.composable.AstraIconButton
import com.makeevrserg.empireprojekt.mobile.core.ui.text.composable.AstraText
import com.makeevrserg.empireprojekt.mobile.core.ui.theme.AdaptThemeFade

@Composable
private fun SearchLeadingIcon() {
    IconButton(
        onClick = { },
        modifier = Modifier.alpha(ContentAlpha.medium)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
private fun SearchTrailingIcon(
    onCloseClick: () -> Unit,
    actions: @Composable () -> Unit
) {
    Row {
        AstraIconButton(
            imageVector = Icons.Filled.Close,
            onClick = onCloseClick,
            modifier = Modifier.alpha(ContentAlpha.medium)
        )
        actions.invoke()
    }
}

@Composable
private fun SearchTextField(
    query: String,
    hint: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    focusRequester: FocusRequester,
    actions: @Composable () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = query,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            AstraText(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = hint,
                color = MaterialTheme.colors.onPrimary
            )
        },
        singleLine = true,
        leadingIcon = { SearchLeadingIcon() },
        trailingIcon = {
            SearchTrailingIcon(
                onCloseClick = {
                    focusRequester.freeFocus()
                    keyboardController?.hide()
                    onCloseClick.invoke()
                },
                actions = actions
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            focusRequester.freeFocus()
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.secondaryVariant,
            textColor = MaterialTheme.colors.onPrimary,
            focusedIndicatorColor = MaterialTheme.colors.secondaryVariant
        )
    )
}

@Composable
fun SearchAppBar(
    query: String,
    onTextChange: (String) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    actions: @Composable () -> Unit = {},
    focusRequester: FocusRequester = remember { FocusRequester() },
    hint: String = "...",
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.systemBarsPadding())
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            elevation = AppBarDefaults.TopAppBarElevation,
            color = MaterialTheme.colors.primaryVariant
        ) {
            val isInspectionMode = LocalInspectionMode.current
            LaunchedEffect(Unit) {
                if (isInspectionMode) return@LaunchedEffect
                focusRequester.requestFocus()
            }
            SearchTextField(
                query = query,
                hint = hint,
                onTextChange = onTextChange,
                onCloseClick = onCloseClick,
                focusRequester = focusRequester,
                actions = actions
            )
        }
    }
}

@Preview
@Composable
private fun SearchAppBarEmptyPreview() {
    AdaptThemeFade {
        SearchAppBar(
            query = "",
            onTextChange = {},
            onCloseClick = {},
            hint = "Search"
        )
    }
}

@Preview
@Composable
private fun SearchAppBarFilledPreview() {
    AdaptThemeFade {
        SearchAppBar(
            query = "RomaRoman",
            onTextChange = {},
            onCloseClick = {},
            hint = "Search"
        )
    }
}
