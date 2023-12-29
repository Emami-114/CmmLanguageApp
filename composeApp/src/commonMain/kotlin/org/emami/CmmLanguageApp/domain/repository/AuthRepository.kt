package org.emami.CmmLanguageApp.domain.repository

import org.emami.CmmLanguageApp.domain.model.AppUser

interface AuthRepository {

    suspend fun doRegister(email: String, password: String): Boolean

    suspend fun doLogin(email: String, password: String): Boolean

    suspend fun fireStoreRegister(user: AppUser,uid:String)
    suspend fun signOut()
}