package com.receiveandconvert.ankanji

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.receiveandconvert.ankanji.compose.FlippableCard
import com.receiveandconvert.ankanji.compose.button.NextIconButton
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.model.enum.JapaneseLevel
import com.receiveandconvert.ankanji.model.enum.LearningType
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme
import kotlinx.coroutines.launch
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
  Scaffold(modifier = Modifier
    .fillMaxSize()
    .padding(5.dp)) { innerPadding ->
    FlashCard(cards, innerPadding)
  }
}

@Composable
fun FlashCard(cards: List<Card>, innerPadding: PaddingValues) {
  val offsetX = remember { Animatable(0f) }
  val scope = rememberCoroutineScope()

  Box(
    modifier = Modifier
      .padding(20.dp)
      .safeContentPadding()
      .fillMaxSize(), contentAlignment = Alignment.Center
  ) {
    if (cards.isEmpty()) return@Box

    var activeCard by remember { mutableStateOf(cards.firstOrNull()) }
    if (activeCard == null) return@Box

    var isFlipped by remember { mutableStateOf(false) }

    // Use key to reset the animation state when the card changes
    key(activeCard) {
      FlippableCard(
        card = activeCard!!, 
        isFlipped = isFlipped,
        onFlipped = { isFlipped = it },
        modifier = Modifier.offset { IntOffset(offsetX.value.toInt(), 0) }
      )
    }

    NextIconButton(
      onClick = {
        scope.launch {
          offsetX.animateTo(
            targetValue = 1000f, // Slide to the right
            animationSpec = tween(durationMillis = 200)
          )
          activeCard = cards[(cards.indexOf(activeCard) + 1) % cards.size]
          isFlipped = false
          offsetX.snapTo(-1000f) // Move to the left off-screen
          offsetX.animateTo(
            targetValue = 0f, // Slide back to the center
            animationSpec = tween(durationMillis = 200)
          )
        }
      },
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .size(75.dp)
    )
  }
}

@Preview
@Composable
fun MainContentPreview() {
  MainContent(
    listOf(
      Card(
        kanji = "日",
        level = JapaneseLevel.N5,
        learningType = LearningType.KANJI,
        onyomi = "ニチ; ジツ -",
        kunyomi = "ひ -び",
        translation = "day, sun, Japan"
      ),
      Card(
        kanji = "月",
        level = JapaneseLevel.N5,
        learningType = LearningType.KANJI,
        onyomi = "ゲツ; ガツ -",
        kunyomi = "つき -",
        translation = "month, moon"
      )
    )
  )
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
