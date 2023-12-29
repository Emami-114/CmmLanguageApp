package org.emami.CmmLanguageApp.presentation.wordScreens

import dev.gitlive.firebase.firestore.Timestamp
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.emami.CmmLanguageApp.useCase.WordUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WordHomeViewModel : ViewModel(), KoinComponent {
    private val useCase: WordUseCase by inject()

    private val _state = MutableStateFlow(WordHomeState())
    val state = _state.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            WordHomeState()
        )

    init {
        fetchWords()
    }

    fun updateWordStatus(
        id: String,
        statusEnum: String,
        exercised: Int,
    ) =
        viewModelScope.launch {
            useCase.updateStatus(id, statusEnum, exercised)
        }

    private fun fetchWords() {
        viewModelScope.launch {
            _state.value.copy(loading = true)
            _state.update {
                it.copy(
                    words = useCase.fetchWords(),
                    loading = false,
                    error = null

                )
            }
        }

    }

    private fun setDefaultState() {
        _state.update {
            it.copy(
                words = listOf(),
                loading = false,
                error = null
            )
        }
    }

}