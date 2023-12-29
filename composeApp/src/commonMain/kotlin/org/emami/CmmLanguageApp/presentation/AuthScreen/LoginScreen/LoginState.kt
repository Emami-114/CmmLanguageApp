package org.emami.CmmLanguageApp.presentation.AuthScreen.LoginScreen

data class LoginState(
    val isLoginSuccess: Boolean = false,
    val emailText: String = "",
    val passwordText: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
