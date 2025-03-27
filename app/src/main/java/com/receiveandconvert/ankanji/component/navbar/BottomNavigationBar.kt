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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.receiveandconvert.ankanji.navigation.Navigation

@Preview
@Composable
private fun Preview() {
	BottomNavigationBar(navController = rememberNavController())
}

@Composable
fun BottomNavigationBar(navController: NavController) {
	BottomAppBar(
		actions = {
			IconButton(onClick = { navController.navigate(Navigation.DECKS.name) }) {
				Icon(Icons.Filled.Star, contentDescription = Navigation.DECKS.username)
			}
			IconButton(onClick = { navController.navigate(Navigation.LISTS.name) }) {
				Icon(Icons.AutoMirrored.Filled.List, contentDescription = Navigation.LISTS.username)
			}
		}
	)
}
