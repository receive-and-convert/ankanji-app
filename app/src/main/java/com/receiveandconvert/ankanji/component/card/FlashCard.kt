package com.receiveandconvert.ankanji.component.card

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.component.FlippableCard
import com.receiveandconvert.ankanji.constant.DummyData.dummyVocabularyCards
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.util.default
import kotlinx.coroutines.launch

@Preview
@Composable
private fun Preview() {
	FlashCard(dummyVocabularyCards, Modifier.default(PaddingValues(16.dp)))
}

@Composable
fun FlashCard(cards: List<Card>, modifier: Modifier = Modifier) {
	val shuffledCards = remember { cards.shuffled() } // Shuffle the cards
	val offsetX = remember { Animatable(0f) }
	val scope = rememberCoroutineScope()
	var isAnimating by remember { mutableStateOf(false) } // Add a flag to track animation state

	Box(
		modifier = modifier
			.fillMaxSize(), contentAlignment = Alignment.Center
	) {
		if (shuffledCards.isEmpty()) return@Box

		var activeCard by remember { mutableStateOf(shuffledCards.firstOrNull()) }
		if (activeCard == null) return@Box

		var isFlipped by remember { mutableStateOf(false) }

		// Use key to reset the animation state when the card changes
		key(activeCard) {
			FlippableCard(
				card = activeCard!!,
				isFlipped = isFlipped,
				onFlipped = { isFlipped = it },
				modifier = Modifier.offset { IntOffset(offsetX.value.toInt(), 0) }
			)
		}

		// Previous card button
		FloatingActionButton(
			onClick = {
				if (!isAnimating) { // Check if an animation is already running
					scope.launch {
						isAnimating = true // Set the flag to true when animation starts
						offsetX.animateTo(
							targetValue = -1000f, // Slide to the left
							animationSpec = tween(durationMillis = 150)
						)
						activeCard = shuffledCards[(shuffledCards.indexOf(activeCard) - 1 + shuffledCards.size) % shuffledCards.size]
						isFlipped = false
						offsetX.snapTo(1000f) // Move to the right off-screen
						offsetX.animateTo(
							targetValue = 0f, // Slide back to the center
							animationSpec = tween(durationMillis = 150)
						)
						isAnimating = false // Reset the flag when animation ends
					}
				}
			},
			containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
			elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
			modifier = Modifier.align(Alignment.BottomStart)
		) {
			Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "PREVIOUS")
		}

		// Next card button
		FloatingActionButton(
			onClick = {
				if (!isAnimating) { // Check if an animation is already running
					scope.launch {
						isAnimating = true // Set the flag to true when animation starts
						offsetX.animateTo(
							targetValue = 1000f, // Slide to the right
							animationSpec = tween(durationMillis = 150)
						)
						activeCard = shuffledCards[(shuffledCards.indexOf(activeCard) + 1) % shuffledCards.size]
						isFlipped = false
						offsetX.snapTo(-1000f) // Move to the left off-screen
						offsetX.animateTo(
							targetValue = 0f, // Slide back to the center
							animationSpec = tween(durationMillis = 150)
						)
						isAnimating = false // Reset the flag when animation ends
					}
				}
			},
			containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
			elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
			modifier = Modifier.align(Alignment.BottomEnd)
		) {
			Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "NEXT")
		}
	}
}
