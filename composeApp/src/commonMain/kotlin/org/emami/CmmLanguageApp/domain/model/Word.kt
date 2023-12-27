package org.emami.CmmLanguageApp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val word: String,
    val meaning: String
)
