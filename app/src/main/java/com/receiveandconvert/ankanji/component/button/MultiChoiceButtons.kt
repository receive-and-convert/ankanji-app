package com.receiveandconvert.ankanji.component.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.receiveandconvert.ankanji.R
import com.receiveandconvert.ankanji.model.card.CardLevel
import com.receiveandconvert.ankanji.model.SegmentedButtonParameter
import com.receiveandconvert.ankanji.util.convertToMap

@Preview
@Composable
private fun Preview() {
	val selectedOptions = toMutableStateListOfBoolean(CardLevel.entries.size, true)

	val options = CardLevel.entries.convertToMap {
		it.name to SegmentedButtonParameter(
			label = { Text(it.name) }
		)
	}
	MultiChoiceButtons(
		options = options,
		selectedOptions = selectedOptions,
		onCheckedChange = { index, checked -> selectedOptions[index] = checked }
	)
}

@Composable
fun toMutableStateListOfBoolean(size: Int, defaultValue: Boolean = false): SnapshotStateList<Boolean> {
	return remember { mutableStateListOf<Boolean>().apply { repeat(size) { add(defaultValue) } } }
}

@Composable
fun MultiChoiceButtons(
	options: MutableMap<String, SegmentedButtonParameter>,
	selectedOptions: List<Boolean>,
	onCheckedChange: (Int, Boolean) -> Unit
) {
	val resetText = stringResource(R.string.reset)
	options.plusAssign(Pair(
		resetText, SegmentedButtonParameter(
		label = { Icon(Icons.Default.Refresh, contentDescription = resetText) }
	)))
	MultiChoiceSegmentedButtonRow {
		options.keys.forEachIndexed { index, key ->
			val checked = if (key == resetText) false else selectedOptions[index]
			val parameter = options[key]
			SegmentedButton(
				shape = SegmentedButtonDefaults.itemShape(
					index = index,
					count = options.size
				),
				checked = checked,
				onCheckedChange = {
					when (key) {
						resetText -> selectedOptions.forEachIndexed { index, _ -> onCheckedChange(index, true) }
						else -> onCheckedChange(index, !checked)
					}
				},
				icon = { SegmentedButtonDefaults.Icon(active = checked) },
				label = parameter?.label ?: {  }
			)
		}
	}
}
