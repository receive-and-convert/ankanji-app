package com.receiveandconvert.ankanji.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.receiveandconvert.ankanji.constant.DummyData.dummyVocabularyCards
import com.receiveandconvert.ankanji.model.Card
import com.receiveandconvert.ankanji.ui.theme.AnkanjiTheme

@Composable
@Preview(showBackground = true)
fun Preview() {
  var isFlipped by remember { mutableStateOf(false) }

  AnkanjiTheme {
    FlippableCard(
      card = dummyVocabularyCards.first(),
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

@Composable
fun FrontCardContent(card: Card) {
  Card(
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    modifier = Modifier.fillMaxSize()
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      ) {
        Text(
          text = card.kanji,
          style = TextStyle(
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }
      Text(
        text = card.level.name,
        fontSize = 12.sp,
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(4.dp)
      )
    }
  }
}

@Composable
fun BackCardContent(card: Card) {
  Card(
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
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
          OutlinedTextField(
            value = card.kana,
            onValueChange = {},
            label = {
              Text(
                text = "Kana",
                color = MaterialTheme.colorScheme.onSecondary
              )
            },
            textStyle = TextStyle.Default.copy(
              color = MaterialTheme.colorScheme.onSecondary,
              fontSize = 24.sp
            ),
            readOnly = true,
            enabled = false
          )
        Text(
          text = card.translation,
          fontSize = 18.sp,
          textAlign = TextAlign.Center,
          color = MaterialTheme.colorScheme.onSecondary
        )
      }
      Text(
        text = card.level.name,
        fontSize = 12.sp,
        modifier = Modifier
          .align(Alignment.TopEnd)
          .padding(4.dp)
      )
    }
  }
}
