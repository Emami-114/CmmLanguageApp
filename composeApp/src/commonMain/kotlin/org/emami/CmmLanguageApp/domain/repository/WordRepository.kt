package org.emami.CmmLanguageApp.domain.repository

import org.emami.CmmLanguageApp.domain.model.Word

interface WordRepository {
    suspend fun fetchWords(): List<Word>
    suspend fun addWord(word: String, meaning: String)
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
    )

    suspend fun updateStatus(id: String, statusEnum: String?, exercised: Int)

    suspend fun deleteWord(id: String)
}