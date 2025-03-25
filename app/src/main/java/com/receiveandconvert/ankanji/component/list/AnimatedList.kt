package com.receiveandconvert.ankanji.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.constant.DummyData.dummyVocabularyCards
import com.receiveandconvert.ankanji.model.Card

@Preview
@Composable
private fun Preview() {
	AnimatedList(dummyVocabularyCards, Modifier.padding(5.dp))
}

@Composable
fun AnimatedList(
	items: List<Card>,
	modifier: Modifier = Modifier
) {
	LazyColumn(modifier) {
		// Use a unique key per item, so that animations work as expected.
		items(count = items.size, key = { it }) { index ->
			val card = items[index]
			ListItem(
				leadingContent = { Text(card.kanji) },
				overlineContent = { Text(card.translation) },
				headlineContent = { Text(card.kana) },
				trailingContent = { Text(card.level.name) },
				supportingContent = { Text(card.usageType) },
				modifier = Modifier
					.fillParentMaxWidth()
					.padding(horizontal = 8.dp, vertical = 0.dp),
			)
		}
	}
}
