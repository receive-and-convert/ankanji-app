package com.receiveandconvert.ankanji.component.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.receiveandconvert.ankanji.enum.Navigation

@Preview
@Composable
private fun Preview() {
	var selectedTab by remember { mutableIntStateOf(Navigation.DECKS.ordinal) }

	BottomNavigationBar(onTabSelected = {
		selectedTab = it
	})
}

@Composable
fun BottomNavigationBar(onTabSelected: (Int) -> Unit) {
	BottomAppBar(
		actions = {
			IconButton(onClick = { onTabSelected(Navigation.DECKS.ordinal) }) {
				Icon(Icons.Filled.Star, contentDescription = Navigation.DECKS.username)
			}
			IconButton(onClick = { onTabSelected(Navigation.LISTS.ordinal) }) {
				Icon(Icons.AutoMirrored.Filled.List, contentDescription = Navigation.LISTS.username)
			}
		}
	)
}
