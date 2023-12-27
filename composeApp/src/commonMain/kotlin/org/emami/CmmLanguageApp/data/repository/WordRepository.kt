package org.emami.CmmLanguageApp.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.emami.CmmLanguageApp.domain.model.Word


class WordRepository {

    suspend fun getWords(): List<Word> {
        val firebaseSession = Firebase.firestore
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
}