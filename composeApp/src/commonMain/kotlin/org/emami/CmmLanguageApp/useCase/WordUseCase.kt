package org.emami.CmmLanguageApp.useCase

import org.emami.CmmLanguageApp.domain.model.Word
import org.emami.CmmLanguageApp.domain.repository.WordRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WordUseCase : KoinComponent {
    private val repository: WordRepository by inject()

    suspend fun fetchWords(): List<Word> {
        return repository.fetchWords()
    }

    suspend fun updateStatus(id: String, statusEnum: String?, exercised: Int) {
        repository.updateStatus(id, statusEnum, exercised)
    }

    suspend fun updateWord(
        id: String,
        word: String,
        meaning: String,
        exercised: Int,
        status: String,
        sentence: String?,
        sentenceMeaning: String?,
        sentence2: String?,
        sentenceMeaning2: String?,
        statusEnum: String?,
        timesTamp: String?
    ) {
        repository.updateWord(
            id,
            word,
            meaning,
            exercised,
            status,
            sentence,
            sentenceMeaning,
            sentence2,
            sentenceMeaning2,
            statusEnum,
            timesTamp
        )
    }
}