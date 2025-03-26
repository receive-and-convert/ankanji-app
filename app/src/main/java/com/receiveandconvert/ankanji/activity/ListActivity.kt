package com.receiveandconvert.ankanji.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.receiveandconvert.ankanji.component.button.MultiChoiceButtons
import com.receiveandconvert.ankanji.component.button.toMutableStateListOfBoolean
import com.receiveandconvert.ankanji.component.list.AnimatedList
import com.receiveandconvert.ankanji.constant.DummyData.dummyCards
import com.receiveandconvert.ankanji.enum.CardLevel
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.SegmentedButtonParameter
import com.receiveandconvert.ankanji.util.convertToMap

@Preview
@Composable
private fun Preview() {
	ListPage(dummyCards)
}

@Composable
fun ListPage(cards: List<Card>, modifier: Modifier = Modifier) {
	val selectedOptions = toMutableStateListOfBoolean(CardLevel.entries.size, true)
	val modifiedCards = cards.filter { selectedOptions[it.level.ordinal] }
		.sortedByDescending { it.level }

	Scaffold(modifier) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			// Buttons that change the value of displayedItems.
			MultiChoiceButtons(
				options = CardLevel.entries.convertToMap {
					it.name to SegmentedButtonParameter(label = { Text(it.name) })
				},
				selectedOptions = selectedOptions,
				onCheckedChange = { index, checked -> selectedOptions[index] = checked }
			)

			// List that displays the values of displayedItems.
			AnimatedList(modifiedCards)
		}
	}
}
