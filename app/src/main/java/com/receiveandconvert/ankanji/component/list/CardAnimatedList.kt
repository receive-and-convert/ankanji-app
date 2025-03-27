package com.receiveandconvert.ankanji.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.component.badge.CardLeveLBadge
import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyVocabularyCards

@Preview
@Composable
private fun CardAnimatedListPreview() {
	CardAnimatedList(dummyVocabularyCards, Modifier.padding(5.dp))
}

@Composable
fun CardAnimatedList(
	items: List<Card>,
	modifier: Modifier = Modifier
) {
	LazyColumn(modifier) {
		// Use a unique key per item, so that animations work as expected.
		items(count = items.size, key = { it }) { index ->
			val item = items[index]
			ListItem(
				leadingContent = { Text(item.kanji) },
				overlineContent = { Text(item.translation) },
				headlineContent = { Text(item.kana) },
				trailingContent = { CardLeveLBadge(item) },
				supportingContent = { Text(item.usageType) },
				modifier = Modifier
					.fillParentMaxWidth()
					.padding(horizontal = 8.dp, vertical = 0.dp),
			)

			HorizontalDivider()
		}
	}

}
