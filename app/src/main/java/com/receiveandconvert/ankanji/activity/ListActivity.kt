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
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.SegmentedButtonParameter
import com.receiveandconvert.ankanji.model.constant.DummyData.DUMMY_CARDS
import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.util.convertToMap

@Preview
@Composable
private fun Preview() {
	ListPage(DUMMY_CARDS)
}

@Composable
fun ListPage(cards: List<Card>, modifier: Modifier = Modifier) {
	val selectedOptions = toMutableStateListOfBoolean(JapaneseLevel.entries.size)

	Scaffold(modifier) { paddingValues ->
		Column(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize()
		) {
			// Buttons that change the value of displayedItems.
			MultiChoiceButtons(
				options = JapaneseLevel.entries.convertToMap {
					it.name to SegmentedButtonParameter(label = { Text(it.name) })
				},
				selectedOptions = selectedOptions,
				onCheckedChange = { index, checked -> selectedOptions[index] = checked }
			)

			// List that displays the values of displayedItems.
			AnimatedList(cards)
		}
	}
}