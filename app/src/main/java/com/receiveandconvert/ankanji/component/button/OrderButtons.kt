package com.receiveandconvert.ankanji.component.button

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
	OrderButtons(
		orderAlphabetically = {  },
		orderByLastUpdated = {  }
	)
}

@Composable
fun OrderButtons(
	orderAlphabetically: () -> Unit,
	orderByLastUpdated: () -> Unit
) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center
	) {
		var selectedIndex by remember { mutableIntStateOf(0) }
		val options = listOf("Alphabetical", "Last Updated")

		SingleChoiceSegmentedButtonRow {
			options.forEachIndexed { index, label ->
				SegmentedButton(
					shape = SegmentedButtonDefaults.itemShape(
						index = index,
						count = options.size
					),
					onClick = {
						Log.d("AnimatedOrderedList", "selectedIndex: $selectedIndex")
						selectedIndex = index
						when (options[selectedIndex]) {
							"Alphabetical" -> orderAlphabetically()
							"Last Updated" -> orderByLastUpdated()
						}
					},
					selected = index == selectedIndex
				) {
					Text(label)
				}
			}
		}
	}
}