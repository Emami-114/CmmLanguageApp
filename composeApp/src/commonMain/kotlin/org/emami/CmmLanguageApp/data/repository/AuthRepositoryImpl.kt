package org.emami.CmmLanguageApp.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.emami.CmmLanguageApp.domain.model.AppUser
import org.emami.CmmLanguageApp.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {

    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    override suspend fun doRegister(email: String, password: String): Boolean {
        return try {
            val newUser = firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).user.let { fbUser ->
                if (fbUser != null) {
                    val firebaseStoreUser = AppUser(
                        uid = fbUser.uid,
                        email = email
                    )
                    fireStoreRegister(firebaseStoreUser, fbUser.uid)
                    fbUser
                } else {
                    null
                }
            }
            newUser != null
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun doLogin(email: String, password: String): Boolean {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password)
            user.user != null
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun fireStoreRegister(user: AppUser, uid: String) {
        try {
            fireStore.collection("user").document(uid)
                .set(user)
        } catch (e: Exception) {
            println("fehler beim firestoreRegister ${e.message}")
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}