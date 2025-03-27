package com.receiveandconvert.ankanji.component.badge

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.receiveandconvert.ankanji.model.card.Card

@Composable
fun CardTypeBadge(card: Card) {
	Badge(
		containerColor = card.type.tint,
		modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(12.dp))
	) {
		Text(
			text = card.type.username,
			fontSize = 12.sp
		)
	}
}