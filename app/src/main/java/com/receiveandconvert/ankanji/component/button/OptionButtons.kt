package com.receiveandconvert.ankanji.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionButtons(spec: List<OptionButtonSpec>) {
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.Center
	) {
		spec.forEachIndexed { index, buttonSpec ->
			Button(
				onClick = buttonSpec.onClick,
				enabled = buttonSpec.enabled
			) {
				Text(buttonSpec.text)
			}
			if (index < spec.size - 1) {
				Spacer(modifier = Modifier.padding(5.dp))
			}
		}
	}
}

data class OptionButtonSpec(
	val enabled: Boolean = true,
	val onClick: () -> Unit = {},
	val text: String
)