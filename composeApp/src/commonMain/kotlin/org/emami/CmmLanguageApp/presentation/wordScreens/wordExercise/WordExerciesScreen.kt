package org.emami.CmmLanguageApp.presentation.wordScreens.wordExercise

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.DefaultRotation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.emami.CmmLanguageApp.domain.model.Word
import org.emami.CmmLanguageApp.presentation.wordScreens.WordHomeState
import org.emami.CmmLanguageApp.presentation.wordScreens.WordHomeViewModel
import org.koin.compose.koinInject

class WordExerciesScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: WordHomeViewModel = koinInject()
        val uiState by viewModel.state.collectAsState()
        var words by remember { mutableStateOf(uiState.words.shuffled()) }
        val navigator: Navigator = LocalNavigator.currentOrThrow
        var index = remember { mutableStateOf(0) }
        val currentWords = try {
            words[index.value]
        } catch (e: Exception) {
            null
        }
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(text = "Exercises", color = Color.White.copy(alpha = 0.7f))
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.7f),
                        modifier = Modifier.clickable {
                            navigator.pop()
                        }
                    )


                },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
            )
        }) {
            if (currentWords != null) {
                WordExerciesContent(viewModel, currentWords, index = index)
            }
        }
    }

    @Composable
    private fun WordExerciesContent(
        viewModel: WordHomeViewModel,
        words: Word,
        index: MutableState<Int>
    ) {
        WordExerciesCell(word = words) {
            val exercies = words.exercised + 1
            viewModel.updateWordStatus(
                id = words.id ?: "",
                statusEnum = it.name,
                exercised = exercies
            )
            index.value = index.value + 1
        }
    }

    @Composable
    private fun WordExerciesCell(
        word: Word,
        onClick: (WordExcerciesEnum) -> Unit
    ) {
        var rotation by remember { mutableStateOf(false) }
        val angle by animateFloatAsState(
            targetValue = if (rotation) 180f else 0f,
            animationSpec = tween(durationMillis = 600, easing = EaseInOut)
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier.fillMaxWidth().padding(30.dp).height(250.dp)
                    .graphicsLayer {
                        rotationY = angle
                        cameraDistance = 30f
                    }
                    .clickable {
                        rotation = !rotation
                    }
                    .clip(RoundedCornerShape(30.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = if (rotation) word.meaning else word.word,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(0.7f),
                        modifier = Modifier.graphicsLayer {
                            rotationY = angle
                        }
                    )
                    if (word.sentence != null) {
                        Text(
                            text = if (rotation) word.sentence ?: "" else word.sentenceMeaning
                                ?: "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White.copy(0.7f),
                            modifier = Modifier.graphicsLayer {
                                rotationY = angle
                            }
                        )
                    }
                    if (word.sentence2 != null) {
                        Text(
                            text = if (rotation) word.sentence2 ?: "" else word.sentenceMeaning2
                                ?: "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White.copy(0.7f),
                            modifier = Modifier.graphicsLayer {
                                rotationY = angle
                            }
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Button(onClick = {
                    onClick(WordExcerciesEnum.HEAVY)
                }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium) {
                    Text(text = "Schwer", color = Color.White.copy(alpha = 0.7f))
                }
                Button(onClick = {
                    onClick(WordExcerciesEnum.GUT)
                }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium) {
                    Text(text = "Gut", color = Color.White.copy(alpha = 0.7f))
                }
                Button(onClick = {
                    onClick(WordExcerciesEnum.EASY)
                }, modifier = Modifier.weight(1f), shape = MaterialTheme.shapes.medium) {
                    Text(text = "Leicht", color = Color.White.copy(alpha = 0.7f))
                }
            }

        }
    }
}

enum class WordExcerciesEnum {
    EASY,
    GUT,
    HEAVY,
}