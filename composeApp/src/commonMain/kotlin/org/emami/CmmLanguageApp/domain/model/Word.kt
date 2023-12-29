package org.emami.CmmLanguageApp.domain.model

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
data class Word(
    var word: String,
    var meaning: String,
    var exercised: Int,
    var status: String,
    var id: String? = null,
    var sentence: String? = null,
    var sentenceMeaning: String? = null,
    var sentence2: String? = null,
    var sentenceMeaning2: String? = null,
    var statusEnum: String? = null,
    var timesTamp: Timestamp = Timestamp.now()
)
