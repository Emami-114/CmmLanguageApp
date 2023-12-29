package org.emami.CmmLanguageApp.presentation.AuthScreen.LoginScreen

sealed interface LoginEvent {
    data object OnLogin : LoginEvent
    data class OnEmailChanged(val value: String) : LoginEvent
    data class OnPasswordChanges(val value: String) : LoginEvent
    data object OnSetDefaultState : LoginEvent
}