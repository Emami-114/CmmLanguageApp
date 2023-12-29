package org.emami.CmmLanguageApp.presentation.AuthScreen.registrationScreen

sealed interface RegisterEvent {
    data object OnRegistration : RegisterEvent
    data class OnEmailChanged(val value: String) : RegisterEvent
    data class OnPasswordChanges(val value: String) : RegisterEvent
    data object OnSetDefaultState : RegisterEvent
}