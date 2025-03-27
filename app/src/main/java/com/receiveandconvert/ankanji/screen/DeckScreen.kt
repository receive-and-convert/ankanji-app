package com.receiveandconvert.ankanji.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.receiveandconvert.ankanji.component.list.DeckAnimatedList
import com.receiveandconvert.ankanji.model.Deck
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyDecks
import com.receiveandconvert.ankanji.navigation.Navigation

@Preview
@Composable
private fun DeckScreenPreview() {
	DeckScreen(
		decks = dummyDecks,
		navController = rememberNavController()
	)
}

@Composable
fun DeckScreen(
	decks: List<Deck>,
	navController: NavController,
	modifier: Modifier = Modifier
) {
	var isNavigating by remember { mutableStateOf(false) }
	Scaffold(modifier) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			// Title
			Text(
				text = "Decks",
				style = MaterialTheme.typography.titleLarge,
				textAlign = TextAlign.Center,
				fontWeight = FontWeight.Bold,
				modifier = Modifier.fillMaxWidth().padding(12.dp)
			)

			// Separator
			HorizontalDivider(
				thickness = 3.dp
			)

			// Decks
			DeckAnimatedList(decks) { deck ->
				if (!isNavigating) {
					isNavigating = true
					navController.navigate("${Navigation.DECK_FLASHCARD.baseRoute}/${deck.id}")
					isNavigating = false
				}
			}
		}
	}
}

