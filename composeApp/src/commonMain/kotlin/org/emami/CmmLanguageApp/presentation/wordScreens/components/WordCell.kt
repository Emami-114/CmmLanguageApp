package org.emami.CmmLanguageApp.presentation.wordScreens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.emami.CmmLanguageApp.domain.model.Word

@Composable
fun WordCell(
    word: Word,
) {
    var rotation by remember { mutableStateOf(false) }
    val angle by animateFloatAsState(
        targetValue = if (rotation) 180f else 0f,
        animationSpec = tween(durationMillis = 600)
    )
    Box(
        modifier = Modifier.fillMaxWidth().heightIn(max = 100.dp)
            .graphicsLayer {
                rotationY = angle
                cameraDistance = 30f
            }.clickable {
                rotation = !rotation
            }.clip(shape = RoundedCornerShape(10.dp)).animateContentSize()
            .background(MaterialTheme.colorScheme.primary).padding(16.dp)

    ) {
        Text(
            text = if (rotation) word.meaning else word.word,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 2,
            modifier = Modifier.fillMaxWidth().graphicsLayer {
                rotationY = angle
            }
        )
    }

}
