package org.emami.CmmLanguageApp.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Timestamp
import dev.gitlive.firebase.firestore.firestore
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.emami.CmmLanguageApp.domain.model.Word
import org.emami.CmmLanguageApp.domain.repository.WordRepository


class WordRepositoryImpl : WordRepository {
    private val firebaseSession = Firebase.firestore

    override suspend fun fetchWords(): List<Word> {
        try {
            val wordResponse = firebaseSession
                .collection("sentences").get()
            return wordResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun addWord(word: String, meaning: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateWord(
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

        val word =
            arrayOf(
                "word" to word,
                "meaning" to meaning,
                "exercised" to exercised,
                "status" to status,
                "sentence" to sentence,
                "sentenceMeaning" to sentenceMeaning,
                "sentence2" to sentence2,
                "sentenceMeaning2" to sentenceMeaning2,
                "statusEnum" to statusEnum,
                "timesTamp" to timesTamp
            )
        firebaseSession.collection("sentences").document(id).update(word)
    }

    override suspend fun updateStatus(
        id: String,
        statusEnum: String?,
        exercised: Int,
    ) {
        val timesTamp = Timestamp.now()
        val word: Map<String, Any?> = mapOf(
            "statusEnum" to statusEnum,
            "exercised" to exercised,
            "timesTamp" to timesTamp
        )
        firebaseSession.collection("sentences").document(id)
            .set(word, merge = true)
    }

    override suspend fun deleteWord(id: String) {
        TODO("Not yet implemented")
    }
}