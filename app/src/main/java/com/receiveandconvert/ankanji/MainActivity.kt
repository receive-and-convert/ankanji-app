package com.receiveandconvert.ankanji

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.receiveandconvert.ankanji.activity.ListPage
import com.receiveandconvert.ankanji.component.card.FlashCard
import com.receiveandconvert.ankanji.component.navbar.BottomNavigationBar
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.constant.DummyData.DUMMY_CARDS
import com.receiveandconvert.ankanji.model.enum.Navigation
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme
import com.receiveandconvert.ankanji.util.add
import com.receiveandconvert.ankanji.util.default
import com.receiveandconvert.ankanji.util.fetchCards

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    // Fetch list of card information
    val cards = resources.fetchCards(R.raw.jlpt_n5)

    setContent {
      AnkanjiTheme {
        MainContent(cards)
      }
    }
  }
}

@Preview
@Composable
private fun Preview() {
  MainContent(DUMMY_CARDS)
}

@Composable
fun MainContent(cards: List<Card>) {
  var selectedTab by remember { mutableIntStateOf(Navigation.DECKS.ordinal) }

  Scaffold(
    modifier = Modifier.fillMaxSize().padding(5.dp),
    bottomBar = { BottomNavigationBar(onTabSelected = { selectedTab = it }) }
  ) { paddingValues ->
    val combinedPaddingValues = paddingValues.add(PaddingValues(10.dp))
    when (selectedTab) {
      0 -> FlashCard(cards, Modifier.default(combinedPaddingValues))
      1 -> ListPage(cards, Modifier.default(combinedPaddingValues))
    }
  }
}
