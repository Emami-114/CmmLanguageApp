package org.emami.CmmLanguageApp.di

import org.emami.CmmLanguageApp.data.repository.AuthRepositoryImpl
import org.emami.CmmLanguageApp.data.repository.WordRepositoryImpl
import org.emami.CmmLanguageApp.domain.repository.AuthRepository
import org.emami.CmmLanguageApp.domain.repository.WordRepository
import org.emami.CmmLanguageApp.presentation.AuthScreen.LoginScreen.LoginViewModel
import org.emami.CmmLanguageApp.presentation.AuthScreen.registrationScreen.RegisterViewModel
import org.emami.CmmLanguageApp.presentation.wordScreens.WordHomeViewModel
import org.emami.CmmLanguageApp.useCase.AuthUseCase
import org.emami.CmmLanguageApp.useCase.WordUseCase
import org.koin.dsl.module

private var domainModule = module {
    factory { WordUseCase() }
    factory { AuthUseCase() }
}

private var presentationModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl()
    }
    single<WordRepository> {
        WordRepositoryImpl()
    }
    single { WordHomeViewModel() }
    single { LoginViewModel() }
    single { RegisterViewModel() }
}

private fun getAllModules() = listOf(
    domainModule, presentationModule
)

fun getSharedModules() = getAllModules()