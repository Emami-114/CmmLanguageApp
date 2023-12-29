package org.emami.CmmLanguageApp.presentation.wordScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.emami.CmmLanguageApp.presentation.wordScreens.wordExercise.WordExerciesScreen
import org.koin.compose.koinInject

class WordHomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: WordHomeViewModel = koinInject()
        val uiState by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(floatingActionButton = {
            Button(
                modifier = Modifier.height(50.dp),
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.6f),
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd, contentDescription = null
                )
            }
        }, topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Home", fontWeight = FontWeight.SemiBold, color = Color.White.copy(0.8f),
                        modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }, modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
            )
        }) {

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize().padding(it).padding(top = 10.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.primary).clickable {
                                navigator.push(WordList())
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Words",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.primary).clickable {
                                navigator.push(WordExerciesScreen())
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Exercises",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Test1",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Test2",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }

    }
}