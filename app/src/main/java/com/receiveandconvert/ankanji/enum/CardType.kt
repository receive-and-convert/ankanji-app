package com.receiveandconvert.ankanji.enum

import com.receiveandconvert.ankanji.enum.CardType.valueOf


enum class CardType(val username: String) {
	KANJI("Kanji"),
	VOCABULARY("Vocabulary"),
	EXPRESSION("Expression");

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
