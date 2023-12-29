package org.emami.CmmLanguageApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.emami.CmmLanguageApp.presentation.wordScreens.WordHomeScreen
import org.emami.CmmLanguageApp.presentation.wordScreens.wordExercise.WordExerciesScreen
import org.emami.CmmLanguageApp.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
internal fun App() = AppTheme {
    KoinContext {
        Surface(modifier = Modifier.fillMaxSize()) {
            Navigator(WordHomeScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}


internal expect fun openUrl(url: String?)