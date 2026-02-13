package com.sjhstudio.dailypulse

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.sjhstudio.dailypulse.screens.AboutScreen
import com.sjhstudio.dailypulse.screens.ArticlesScreen
import com.sjhstudio.dailypulse.screens.Screen
import com.sjhstudio.dailypulse.shared.articles.ArticlesViewModel

@Composable
internal fun AppNav(viewModel: ArticlesViewModel) {
    val backStack = remember { mutableStateListOf<Any>(Screen.Articles) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.Articles> {
                ArticlesScreen(
                    viewModel = viewModel,
                    onAboutButtonClick = { backStack.add(Screen.About) }
                )
            }
            entry<Screen.About> {
                AboutScreen(onBackClick = { backStack.removeLastOrNull() })
            }
        }
    )
}