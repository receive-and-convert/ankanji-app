package com.receiveandconvert.ankanji.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NextIconButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
  IconButton(
    onClick = onClick,
    modifier = modifier.background(color = MaterialTheme.colorScheme.primary, shape = CircleShape)
  ) {
    Icon(
      imageVector = Icons.AutoMirrored.Filled.ArrowForward,
      contentDescription = "Next",
      tint = MaterialTheme.colorScheme.onPrimary
    )
  }
}
