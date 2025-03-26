package com.receiveandconvert.ankanji.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.receiveandconvert.ankanji.model.card.Card
import com.receiveandconvert.ankanji.model.card.CardType
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyCard
import com.receiveandconvert.ankanji.model.constant.DummyData.dummyCards
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme
import com.receiveandconvert.ankanji.ui.theme.ExpressionBackCardColor
import com.receiveandconvert.ankanji.ui.theme.ExpressionFrontCardColor
import com.receiveandconvert.ankanji.ui.theme.KanjiCardBackColor
import com.receiveandconvert.ankanji.ui.theme.KanjiCardFrontColor
import com.receiveandconvert.ankanji.ui.theme.VocabularyBackCardColor
import com.receiveandconvert.ankanji.ui.theme.VocabularyFrontCardColor

@Preview(widthDp = 360)
@Composable
fun FlippableCardPreview() {
  var isFlipped by remember { mutableStateOf(false) }

  AnkanjiTheme {
    FlippableCard(
      card = dummyCards.first(),
      isFlipped = isFlipped,
      onFlipped = { isFlipped = it }
    )
  }
}

@Composable
fun FlippableCard(
  card: Card, 
  isFlipped: Boolean, 
  onFlipped: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val rotation by animateFloatAsState(if (isFlipped) 180f else 0f, visibilityThreshold = 0.1f)

  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(350.dp)
      .graphicsLayer {
        rotationY = rotation
        cameraDistance = 12f * density
      }
      .clickable { onFlipped(!isFlipped) },
    contentAlignment = Alignment.Center
  ) {
    if (rotation <= 90f) {
      FrontCardContent(card)
    } else {
      BackCardContent(card)
    }
  }
}

@Preview(widthDp = 175, heightDp = 175)
@Composable
fun FrontCardPreview() {
  FrontCardContent(dummyCard)
}

@Composable
fun FrontCardContent(card: Card) {
  Card(
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(
      containerColor = card.determineContainerColor(false),
      contentColor = Color.Black
    ),
    modifier = Modifier.fillMaxSize()
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      ) {
        if (card.type == CardType.VOCABULARY) {
          Text(
            text = card.kanji,
            style = TextStyle(
              fontSize = 15.sp,
              fontWeight = FontWeight.SemiBold,
              textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
          )
        }
        Text(
          text = if (card.type != CardType.VOCABULARY && card.kanji.isNotEmpty()) card.kanji else card.kana,
          style = TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(4.dp)
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Badge(containerColor = card.type.tint, modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp))) {
            Text(
              text = card.type.username,
              fontSize = 12.sp
            )
          }
          Badge(containerColor = card.level.tint, modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp))) {
            Text(
              text = card.level.name,
              fontSize = 12.sp
            )
          }
        }
      }
    }
  }
}

@Preview(widthDp = 175, heightDp = 175)
@Composable
fun BackCardPreview() {
  Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
    BackCardContent(dummyCard)
  }
}

@Composable
fun BackCardContent(card: Card) {
  Card(
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(
      containerColor = card.determineContainerColor(true),
      contentColor = Color.Black
    ),
    modifier = Modifier
      .fillMaxSize()
      .graphicsLayer { rotationY = 180f }
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .align(Alignment.Center)
          .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = card.kana,
          fontSize = 30.sp
        )
        Text(
          text = card.translation,
          fontSize = 18.sp,
          textAlign = TextAlign.Center
        )
      }
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(4.dp)
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Badge(containerColor = Color.White, modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp))) {
            Text(
              text = card.type.username,
              fontSize = 12.sp
            )
          }
          Badge(containerColor = card.level.tint, modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp))) {
            Text(
              text = card.level.name,
              fontSize = 12.sp
            )
          }
        }
      }
    }
  }
}

@Composable
private fun Card.determineContainerColor(isFlipped: Boolean) = when (type) {
  CardType.EXPRESSION -> if (!isFlipped) ExpressionFrontCardColor else ExpressionBackCardColor
  CardType.KANJI -> if (!isFlipped) KanjiCardFrontColor else KanjiCardBackColor
  CardType.VOCABULARY -> if (!isFlipped) VocabularyFrontCardColor else VocabularyBackCardColor
}
