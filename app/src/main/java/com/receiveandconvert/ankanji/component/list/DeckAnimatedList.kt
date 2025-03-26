package com.receiveandconvert.ankanji.component.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.model.Deck
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyDecks
import com.receiveandconvert.ankanji.ui.theme.PlayIconColor

@Preview
@Composable
private fun DeckAnimatedListPreview() {
	DeckAnimatedList(
		items = dummyDecks,
		modifier = Modifier.padding(5.dp),
		onClick = {}
	)
}

@Composable
fun DeckAnimatedList(
	items: List<Deck>,
	modifier: Modifier = Modifier,
	onClick: (Deck) -> Unit = {}
) {
	LazyColumn(modifier) {
		// Use a unique key per item, so that animations work as expected.
		items(count = items.size, key = { it }) { index ->
			val item = items[index]
			ListItem(
				headlineContent = { Text(item.name) },
				trailingContent = {
					IconButton(
						onClick = { onClick(item) }
					) {
						Icon(Icons.Filled.PlayArrow,
							contentDescription = "Play",
							tint = PlayIconColor
						)
					}
				},
				supportingContent = { Text(item.description) },
				modifier = Modifier
					.fillParentMaxWidth()
					.padding(horizontal = 8.dp, vertical = 0.dp),
			)
			HorizontalDivider()
		}
	}
}
