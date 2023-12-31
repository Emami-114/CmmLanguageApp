package org.emami.CmmLanguageApp.useCase

import org.emami.CmmLanguageApp.domain.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend fun doRegistration(email: String, password: String, onSuccess: () -> Unit) {
        try {
            repository.doRegister(email, password).let { isSuccess ->
                if (isSuccess) {
                    onSuccess()
                } else {
                    throw Exception("Oh, something went wrong!")
                }
            }
        } catch (e: Exception) {
            throw Exception("Error doRegistration: ${e.message}")
        }
    }

    suspend fun doLogin(email: String, password: String, onSuccess: () -> Unit) {
        try {
            repository.doLogin(email, password).let { isSuccess ->
                if (isSuccess) {
                    onSuccess()
                } else {
                    throw Exception("Oh, something went wrong!")
                }
            }
        } catch (e: Exception) {
            throw Exception("Error doLogin: ${e.message}")
        }
    }

    suspend fun signOut() {
        repository.signOut()
    }

}