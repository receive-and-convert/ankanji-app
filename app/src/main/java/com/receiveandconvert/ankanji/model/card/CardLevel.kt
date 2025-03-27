package com.receiveandconvert.ankanji.model.card

import androidx.compose.ui.graphics.Color
import com.receiveandconvert.ankanji.model.card.CardLevel.valueOf
import com.receiveandconvert.ankanji.ui.theme.N1Color
import com.receiveandconvert.ankanji.ui.theme.N2Color
import com.receiveandconvert.ankanji.ui.theme.N3Color
import com.receiveandconvert.ankanji.ui.theme.N4Color
import com.receiveandconvert.ankanji.ui.theme.N5Color

enum class CardLevel(val tint: Color) {
	N5(N5Color),
	N4(N4Color),
	N3(N3Color),
	N2(N2Color),
	N1(N1Color);

	companion object {
		val availableLevel = listOf(N5, N4)

		fun safeValueOf(value: String): CardLevel {
			return try {
				valueOf(value.uppercase())
			} catch (e: IllegalArgumentException) {
				N5
			}
		}
	}
}
