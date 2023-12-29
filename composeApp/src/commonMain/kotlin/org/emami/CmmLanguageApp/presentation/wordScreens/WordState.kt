package org.emami.CmmLanguageApp.presentation.wordScreens

import org.emami.CmmLanguageApp.domain.model.Word

data class WordHomeState(
    val words: List<Word> = listOf(),
    val loading: Boolean = false,
    val error: String? = null,
)