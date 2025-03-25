package com.receiveandconvert.ankanji.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.constant.DummyData.DUMMY_CARDS

@Preview
@Composable
private fun Preview() {
	AnimatedList(DUMMY_CARDS, Modifier.padding(5.dp))
}

@Composable
fun AnimatedList(
	items: List<Card>,
	modifier: Modifier = Modifier
) {
	LazyColumn(modifier) {
		// Use a unique key per item, so that animations work as expected.
		items(count = items.size, key = { it }) { index ->
			ListItem(
				headlineContent = { Text("[${items[index].fullReading}]") },
				leadingContent = { Text(items[index].kanji) },
				modifier = Modifier
					.fillParentMaxWidth()
					.padding(horizontal = 8.dp, vertical = 0.dp),
			)
		}
	}
}