package com.receiveandconvert.ankanji

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.receiveandconvert.ankanji.compose.card.FlashCard
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.constant.DummyData.DUMMY_CARDS
import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.model.enum.LearningType
import com.receiveandconvert.ankanji.model.enum.Navigation
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme
import com.receiveandconvert.ankanji.util.add
import com.receiveandconvert.ankanji.util.default
import java.io.BufferedReader
import java.io.InputStreamReader

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

@Composable
fun MainContent(cards: List<Card>) {
  var selectedTab by remember { mutableIntStateOf(0) }

  Scaffold(
    modifier = Modifier.fillMaxSize().padding(5.dp),
    bottomBar = { BottomNavigationBar(onTabSelected = { selectedTab = it }) }
  ) { paddingValues ->
    val combinedPaddingValues = paddingValues.add(PaddingValues(10.dp))
    when (selectedTab) {
      0 -> FlashCard(cards, Modifier.default(combinedPaddingValues))
      1 -> ListAnimatedItemsExample(cards, Modifier.default(combinedPaddingValues))
    }
  }
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

@Composable
private fun ListAnimatedItemsExample(
  data: List<Card>,
  modifier: Modifier = Modifier,
  onAddItem: () -> Unit = {},
  onRemoveItem: () -> Unit = {},
  resetOrder: () -> Unit = {},
  onSortAlphabetically: () -> Unit = {},
  onSortByLength: () -> Unit = {},
) {
  val canAddItem = data.size < 10
  val canRemoveItem = data.isNotEmpty()

  Scaffold(modifier) { paddingValues ->
    Column(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize()
    ) {
      // Buttons that change the value of displayedItems.
      AddRemoveButtons(canAddItem, canRemoveItem, onAddItem, onRemoveItem)
      OrderButtons(resetOrder, onSortAlphabetically, onSortByLength)

      // List that displays the values of displayedItems.
      ListAnimatedItems(data)
    }
  }
}

@Composable
fun ListAnimatedItems(
  items: List<Card>,
  modifier: Modifier = Modifier
) {
  LazyColumn(modifier) {
    // Use a unique key per item, so that animations work as expected.
    items(count = items.size, key = { it }) {
      ListItem(
        headlineContent = { Text("test-$it") },
        modifier = Modifier
          .animateItem(
            // Optionally add custom animation specs
          )
          .fillParentMaxWidth()
          .padding(horizontal = 8.dp, vertical = 0.dp),
      )
    }
  }
}

@Composable
private fun AddRemoveButtons(
  canAddItem: Boolean,
  canRemoveItem: Boolean,
  onAddItem: () -> Unit,
  onRemoveItem: () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center
  ) {
    Button(enabled = canAddItem, onClick = onAddItem) {
      Text("Add Item")
    }
    Spacer(modifier = Modifier.padding(25.dp))
    Button(enabled = canRemoveItem, onClick = onRemoveItem) {
      Text("Delete Item")
    }
  }
}

@Composable
private fun OrderButtons(
  resetOrder: () -> Unit,
  orderAlphabetically: () -> Unit,
  orderByLength: () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center
  ) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Reset", "Alphabetical", "Length")

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
              "Reset" -> resetOrder()
              "Alphabetical" -> orderAlphabetically()
              "Length" -> orderByLength()
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

@Preview
@Composable
fun MainContentPreview() {
  MainContent(DUMMY_CARDS)
}

private fun Resources.fetchCards(resourceId: Int): List<Card> {
  val reader = BufferedReader(InputStreamReader(openRawResource(resourceId)))
  val parser = CSVParserBuilder().withSeparator(';').build()
  val csvReader = CSVReaderBuilder(reader).withCSVParser(parser).build()

  val cards = mutableListOf<Card>()
  csvReader.use {
    var nextLine = it.readNext()
    while (nextLine != null) {
      cards += Card(
        kanji = nextLine[0],
        level = JapaneseLevel.valueOf(nextLine[1]),
        learningType = LearningType.valueOf(nextLine[2]),
        onyomi = nextLine[3],
        kunyomi = nextLine[4],
        translation = nextLine[5]
      )
      nextLine = it.readNext()
    }
  }
  return cards
}

