package com.receiveandconvert.ankanji.model.card

import androidx.compose.ui.graphics.Color
import com.receiveandconvert.ankanji.model.card.CardType.valueOf
import com.receiveandconvert.ankanji.ui.theme.ExpressionBackCardColor
import com.receiveandconvert.ankanji.ui.theme.KanjiCardBackColor
import com.receiveandconvert.ankanji.ui.theme.VocabularyBackCardColor


enum class CardType(val username: String, val tint: Color) {
	KANJI("K", KanjiCardBackColor),
	VOCABULARY("V", VocabularyBackCardColor),
	EXPRESSION("E", ExpressionBackCardColor);

	companion object {
		fun safeValueOf(value: String): CardType {
			return try {
				valueOf(value.uppercase())
			} catch (e: IllegalArgumentException) {
				VOCABULARY
			}
		}
	}
}
