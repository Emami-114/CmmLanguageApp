package org.emami.CmmLanguageApp.domain.model

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class AppUser(
    val uid: String,
    val email: String,
    val registrationDate: Timestamp = Timestamp.now()
)
