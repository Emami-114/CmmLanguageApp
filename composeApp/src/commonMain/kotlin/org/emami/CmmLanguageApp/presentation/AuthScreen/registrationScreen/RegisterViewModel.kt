package org.emami.CmmLanguageApp.presentation.AuthScreen.registrationScreen

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.emami.CmmLanguageApp.useCase.AuthUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RegisterViewModel : ViewModel(), KoinComponent {
    private val useCase: AuthUseCase by inject()

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RegisterState()
        )

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.OnRegistration -> doRegisteration(event)
            is RegisterEvent.OnEmailChanged -> doEmailChanged(event)
            is RegisterEvent.OnPasswordChanges -> doPasswordChanged(event)
            is RegisterEvent.OnSetDefaultState -> setDefaultsState()
        }
    }

    private fun doRegisteration(event: RegisterEvent.OnRegistration) {
        when {
            _state.value.emailText.isBlank() || _state.value.emailText.isEmpty() -> {
                _state.update {
                    it.copy(emailError = "Email is blank or empty")
                }
            }

            _state.value.passwordText.isBlank() || _state.value.passwordText.isEmpty() -> {
                _state.update {
                    it.copy(passwordError = "Email is blank or empty")
                }
            }

            else -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(isLoading = true)

                    try {
                        useCase.doRegistration(
                            email = _state.value.emailText,
                            password = _state.value.passwordText
                        ) {
                            _state.update {
                                it.copy(
                                    isRegistrationSuccess = true,
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }
                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                isRegistrationSuccess = false,
                                isLoading = false,
                                errorMessage = e.message ?: "some error"
                            )
                        }
                        delay(4000)
                        setDefaultsState()
                    }
                }
            }
        }
    }

    private fun doEmailChanged(event: RegisterEvent.OnEmailChanged) {
        _state.update {
            it.copy(
                emailText = event.value,
                emailError = null
            )
        }
    }

    private fun doPasswordChanged(event: RegisterEvent.OnPasswordChanges) {
        _state.update {
            it.copy(
                passwordText = event.value,
                passwordError = null
            )
        }
    }

    private fun setDefaultsState() {
        _state.update {
            it.copy(
                isRegistrationSuccess = false,
                emailText = "",
                passwordText = "",
                emailError = null,
                passwordError = null,
                isLoading = false,
                errorMessage = null
            )
        }
    }


}