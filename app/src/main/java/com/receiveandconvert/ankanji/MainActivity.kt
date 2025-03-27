package com.receiveandconvert.ankanji

import AppNavigationGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.receiveandconvert.ankanji.provider.CardProviderImpl
import com.receiveandconvert.ankanji.provider.DeckProviderImpl
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContent {
      AnkanjiTheme {
        AppNavigationGraph(
					navController = rememberNavController(),
					cardProvider = CardProviderImpl(resources = resources),
					deckProvider = DeckProviderImpl()
				)
      }
    }
  }
}

@Preview
@Composable
private fun Preview() {
  AppNavigationGraph(
		navController = rememberNavController(),
		cardProvider = CardProviderImpl(LocalContext.current.resources),
		deckProvider = DeckProviderImpl()
	)
}

