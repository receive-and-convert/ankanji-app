
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.receiveandconvert.ankanji.component.card.FlashCard
import com.receiveandconvert.ankanji.component.navbar.BottomNavigationBar
import com.receiveandconvert.ankanji.navigation.Navigation
import com.receiveandconvert.ankanji.provider.CardProvider
import com.receiveandconvert.ankanji.provider.CardProviderImpl
import com.receiveandconvert.ankanji.provider.DeckProvider
import com.receiveandconvert.ankanji.provider.DeckProviderImpl
import com.receiveandconvert.ankanji.screen.DeckScreen
import com.receiveandconvert.ankanji.screen.ListScreen
import com.receiveandconvert.ankanji.util.add

@Preview
@Composable
private fun AppNavGraphPreview() {
  AppNavigationGraph(
    navController = rememberNavController(),
    cardProvider = CardProviderImpl(LocalContext.current.resources),
    deckProvider = DeckProviderImpl()
  )
}

@Composable
fun AppNavigationGraph(
  navController: NavHostController,
  cardProvider: CardProvider,
  deckProvider: DeckProvider,
  paddingValues: PaddingValues = PaddingValues(0.dp)
) {
  Scaffold(
    modifier = Modifier.padding(paddingValues),
    bottomBar = { BottomNavigationBar(navController) }
  ) { innerPadding ->
    val combinedPaddingValues = innerPadding.add(PaddingValues(10.dp))
    NavHost(
			navController = navController,
			startDestination = Navigation.DECKS.baseRoute,
			modifier = Modifier.padding(combinedPaddingValues)
		) {
      composable(Navigation.DECKS.baseRoute) { DeckScreen(deckProvider.getDecks(), navController) }
      composable(Navigation.LISTS.baseRoute) { ListScreen(cardProvider.getCards()) }
      composable("${Navigation.DECK_FLASHCARD.baseRoute}/{deckId}") { backStackEntry ->
        val cards = cardProvider.getCards()
        FlashCard(cards)
      }
    }
  }
}
